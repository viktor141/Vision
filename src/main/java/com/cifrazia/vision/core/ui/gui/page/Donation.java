package com.cifrazia.vision.core.ui.gui.page;

import com.cifrazia.vision.Vision;
import com.cifrazia.vision.connection.auth.AuthorizedClient;
import com.cifrazia.vision.connection.data.element.privilege.*;
import com.cifrazia.vision.core.abstracts.Gui;
import com.cifrazia.vision.core.abstracts.Screen;
import com.cifrazia.vision.core.abstracts.ScrollableScreen;
import com.cifrazia.vision.core.ui.buttons.BuyPrivilegeButton;
import com.cifrazia.vision.core.ui.buttons.donations.*;
import com.cifrazia.vision.core.ui.gui.PlayerInfoPanel;
import com.cifrazia.vision.core.ui.gui.information.KitItems;
import com.cifrazia.vision.core.ui.util.Color;
import com.cifrazia.vision.core.ui.util.Element;
import com.cifrazia.vision.core.ui.util.draw.ScissoredDraw;
import com.cifrazia.vision.core.ui.util.panel.InformationPanel;
import com.cifrazia.vision.core.ui.util.panel.KitPanel;
import com.cifrazia.vision.menu.ModalWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Donation extends ScrollableScreen {

    private final List<DonationButton> donationButtons = new ArrayList<>();
    private final AuthorizedClient client;
    private final BuyPrivilegeButton buyForOneMonth;
    private final BuyPrivilegeButton buyForThreeMonths;
    private final PlayerInfoPanel playerInfoPanel;
    private final Kits kits;
    private final ScaledResolution scaledResolution;
    private final InformationPanel commands;
    private final InformationPanel possibilities;
    private ScissoredDraw scissoredDraw;
    private FormattedPrivileges privileges;
    private HashMap<String, RoleFormatedDescription> roleHashMap;
    private ItemStack tooltipItem = ItemStack.EMPTY;


    public Donation(Minecraft mc, Gui paerentGui, int screenStartX, int screenStartY) {
        super(0, 0);
        this.mc = mc;
        this.screenStartX = screenStartX;
        this.screenStartY = screenStartY;
        client = (AuthorizedClient) Vision.getInstance().getAuthorization();
        playerInfoPanel = new PlayerInfoPanel(mc, true);

        scaledResolution = new ScaledResolution(mc);

        commands = new InformationPanel(mc, "Available commands", scaledResolution);
        possibilities = new InformationPanel(mc, "Opportunities", scaledResolution);

        addDonationButton(new MasterButton(this, donationButtons, 0, 0));
        addDonationButton(new HeroButton(this, donationButtons, 0, 0));
        addDonationButton(new LegendButton(this, donationButtons, 0, 0));
        addDonationButton(new ImmortalButton(this, donationButtons, 0, 0));

        addButton(buyForOneMonth = new BuyPrivilegeButton(this, 0, 0, ""));
        addButton(buyForThreeMonths = new BuyPrivilegeButton(this, 0, 0, ""));

        kits = new Kits(mc, paerentGui, client, this);
    }

    public void privilegeListUpdate() {
        CompletableFuture.supplyAsync(() -> client.getPrivilegeData().getPrivilegeRoles()).thenAccept((data) -> {
            synchronized (client.getPrivilegeData()) {
                privileges = data;
                List<PrivilegeRole> roles = privileges.getRawPrivileges().getRoles();

                roleHashMap = new HashMap<>(roles.size());

                for (int i = 0; i < roles.size(); i++) {
                    roleHashMap.put(roles.get(i).getDisplay_name().toUpperCase(), privileges.getFormedPrivileges().get(i));
                }

                for (int i = 0; i < donationButtons.size(); i++) {
                    DonationButton button = donationButtons.get(i);
                    RoleFormatedDescription role = roleHashMap.get(button.getName());
                    List<Element> commandsElements = new ArrayList<>(role.getCommandsElements().size());
                    List<Element> possibilitiesElements = new ArrayList<>(role.getPossibilitiesElements().size());

                    role.getCommandsElements().forEach(element -> commandsElements
                            .add(new Element(mc, element.getText(), element.getUnderText(), Color.GREEN, Color.GRAY_COMMAND_DESCRIPTION)));

                    role.getPossibilitiesElements().forEach(element -> possibilitiesElements
                            .add(new Element(mc, element.getText(), Color.GREEN)));


                    if (donationButtons.size() > (i + 1)) {
                        RoleFormatedDescription nextRole = roleHashMap.get(donationButtons.get(i + 1).getName());

                        if (role.getCommandsElements().size() < nextRole.getCommandsElements().size()) {
                            RoleFormatedDescription.ElementFormed element = findNewComponent(role.getCommandsElements(), nextRole.getCommandsElements());
                            if (element != null)
                                commandsElements.add(new Element(mc,
                                        element.getUnderText(),
                                        "Buy " + nextRole.getRole().getDisplay_name(),
                                        Color.GRAY_COMMAND_DESCRIPTION, donationButtons.get(i + 1).getColor(), false));
                        }

                        if (role.getPossibilitiesElements().size() < nextRole.getPossibilitiesElements().size()) {
                            RoleFormatedDescription.ElementFormed element = findNewComponent(role.getPossibilitiesElements(), nextRole.getPossibilitiesElements());
                            if (element != null)
                                possibilitiesElements.add(new Element(mc,
                                        element.getText(),
                                        "Buy " + nextRole.getRole().getDisplay_name(),
                                        Color.GRAY_COMMAND_DESCRIPTION, donationButtons.get(i + 1).getColor(), false));
                        }

                    }

                    List<String> kitCodeNames = getPrivilegeKits(role.getRole());

                    button.setEvent(() -> {
                        commands.setElements(commandsElements);

                        possibilities.setElements(possibilitiesElements);

                        kits.updateKits(kitCodeNames);

                        scrollBarUpdate();
                        scrollBar.update();
                    });
                }

                donationButtons.get(0).onClick();
            }
        });
    }

    private RoleFormatedDescription.ElementFormed findNewComponent(List<RoleFormatedDescription.ElementFormed> previous, List<RoleFormatedDescription.ElementFormed> next) {
        List<RoleFormatedDescription.ElementFormed> result = new ArrayList<>(next);

        previous.forEach(result::remove);

        if (!result.isEmpty()) {
            return result.get(result.size() - 1);
        } else {
            return null;
        }
    }

    private void scrollBarUpdate() {
        scrollBar.setContentHeight(Math.max(commands.getHeight(), possibilities.getHeight()) + 100);
    }


    private List<String> getPrivilegeKits(PrivilegeRole role) {
        List<PrivilegeRole.PermissionOfRole> permissions = getKitPermission(role);
        List<String> kitCodeNames = new ArrayList<>(permissions.size());
        permissions.forEach(permission -> kitCodeNames.add(permission.getCode_name()));

        return kitCodeNames;
    }

    private List<PrivilegeRole.PermissionOfRole> getKitPermission(PrivilegeRole role) {
        if (role == null) return Collections.emptyList();

        List<PrivilegeRole.PermissionOfRole> permissions = role.getPerms().stream().
                filter(permissionOfRole -> permissionOfRole.getCode_name().startsWith("kit."))
                .collect(Collectors.toList());

        if (role.getInheritRole() != null) {
            RoleFormatedDescription roleDescription = roleHashMap.get(role.getInheritRole().getDisplay_name().toUpperCase());
            if (roleDescription != null)
                permissions.addAll(getKitPermission(roleDescription.getRole()));
        }

        return permissions;
    }

    @Override
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
        screenEndX = width;
        screenEndY = height;

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        int gap = 20 >> 1;

        buyForOneMonth.updateCords(screenEndX - buyForOneMonth.width, screenStartY + (screenHeight >> 1) - gap - buyForOneMonth.height);
        buyForThreeMonths.updateCords(screenEndX - buyForThreeMonths.width, screenStartY + (screenHeight >> 1) + gap);

        int panelWidth = (int) (width * 0.28125);
        int x = screenStartX + (40 >> 1);
        int y = screenStartY + (128 >> 1);
        commands.setUp(x, y, panelWidth);
        possibilities.setUp(x + panelWidth + (18 >> 1), y, panelWidth);

        kits.setUp(x + ((panelWidth + (18 >> 1)) * 2), y);
        kits.setResolution(width, height);

        playerInfoPanel.setUp(screenEndX - (30 >> 1) - (56 >> 1), screenStartY + (18 >> 1));

        for (int i = 0; i < donationButtons.size(); i++) {
            DonationButton button = donationButtons.get(i);
            button.updateCords(screenStartX + (40 >> 1) + (i * (button.width + (20 >> 1))), screenStartY + (20 >> 1));
        }

        scissoredDraw = new ScissoredDraw(mc, x, y - (30 >> 1), screenWidth, screenHeight);
        scrollBar.setResolution(screenHeight, screenHeight, screenHeight);
        scrollBarUpdate();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //super.drawScreen(mouseX, mouseY, partialTicks);

        for (GuiButton guiButton : this.buttonList) {
            guiButton.drawButton(this.mc, mouseX, mouseY, partialTicks);
        }

        scissoredDraw.draw(() -> {
            int y = screenStartY + (128 >> 1);

            commands.updateY(y + scrollBar.getScrollOffset());
            commands.draw(mouseX, mouseY);

            possibilities.updateY(y + scrollBar.getScrollOffset());
            possibilities.draw(mouseX, mouseY);

            kits.updateY(y + scrollBar.getScrollOffset());
            kits.drawScreen(mouseX, mouseY, partialTicks);
        });

        playerInfoPanel.drawScreen(mouseX, mouseY, partialTicks);

        drawItemTooltip(mouseX, mouseY);
    }

    protected void drawItemTooltip(int mouseX, int mouseY) {
        super.drawItemTooltip(mouseX, mouseY, tooltipItem);
        tooltipItem = ItemStack.EMPTY;
    }

    public void setItemForTooltip(ItemStack item) {
        tooltipItem = item;
    }

    private void addDonationButton(DonationButton donationButton) {
        donationButtons.add(donationButton);
        addButton(donationButton);
    }

    public void updateContentHeight(int contentHeight) {
        scrollBar.setContentHeight(Math.max(contentHeight + 100, scrollBar.getContentHeight()));
        scrollBar.update();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        kits.mouseClicked(mouseX, mouseY, mouseButton);
    }
}

class Kits extends Screen {
    private final AuthorizedClient client;
    private final Donation donation;
    private final Gui parentGui;
    private HashMap<String, KitHolder> kitsHolder;
    private List<KitPanel> panels = new ArrayList<>();
    private int frameWidth;
    private int panelHeight;
    private int frameHeight;

    public Kits(Minecraft mc, Gui parentGui, AuthorizedClient client, Donation donation) {
        this.mc = mc;
        this.parentGui = parentGui;
        this.client = client;
        this.donation = donation;
    }

    public void updateKits(List<String> allowedKits) {
        CompletableFuture.supplyAsync(() -> client.getKitsData().getKits()).thenAccept(data -> {
            synchronized (client.getKitsData()) {
                kitsHolder = data;

                List<KitPanel> panels = new ArrayList<>(kitsHolder.size());

                allowedKits.forEach(kitCodeName -> {
                    KitHolder kit = kitsHolder.get(kitCodeName);
                    panels.add(new KitPanel(mc, kit, donation));
                });

                this.panels = panels;
                updatePanels();

                donation.updateContentHeight(panels.size() * (frameHeight + (10 >> 1)));
            }
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (KitPanel panel : panels) {
            panel.draw(mouseX, mouseY);
        }
    }

    @Override
    public void setResolution(int width, int height) {
        this.width = width;
        this.height = height;
        screenEndX = width;
        screenEndY = height;

        frameWidth = (int) (width * 0.20625f);
        frameHeight = (int) (height * 0.231f);

        screenWidth = screenEndX - screenStartX;
        screenHeight = screenEndY - screenStartY;

        updatePanels();
    }

    public void setUp(int x, int y) {
        screenStartX = x;
        screenStartY = y;
    }

    public void updateY(int y) {
        screenStartY = y;
        updatePanels();
    }

    private void updatePanels() {
        panelHeight = (panels.size() * (frameHeight + 6));

        for (int i = 0; i < panels.size(); i++) {
            panels.get(i).setUp(screenStartX, screenStartY + (i * (frameHeight + (10 >> 1))), frameWidth, frameHeight);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        if (mouseButton != 0) return;
        for (KitPanel panel : panels) {
            if (panel.isMouseBounding(mouseX, mouseY)) {
                mc.displayGuiScreen(new ModalWindow(new KitItems(mc, parentGui, panel.getKitHolder(), panel.getDescription())));
                break;
            }
        }
    }

    public int getHeight() {
        return panelHeight;
    }
}
