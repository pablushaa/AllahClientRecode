package ru.pablusha.AllahRecode;

import net.minecraft.client.Minecraft;
import net.minecraft.world.gen.FlatGeneratorInfo;
import ru.pablusha.AllahRecode.Modules.Combat.KillAura;
import ru.pablusha.AllahRecode.Modules.Movement.Fly;
import ru.pablusha.AllahRecode.Modules.Render.TargetHUD;
import ru.pablusha.AllahRecode.Utils.ChatUtil;

public class CommandMgr {
	public void onCommand(String cmd) {
        Minecraft mc = Minecraft.getMinecraft();
		
		cmd = cmd.replace("#", "");
		
		String[] args = cmd.split(" ");
		switch (args[0]) {
		case "help":
			ChatUtil.sendClientMessage("List of commands:");
			ChatUtil.sendLocalMessage("");
			ChatUtil.sendLocalMessage("§6clear§r > clears chat");
			ChatUtil.sendLocalMessage("§6ip§r > get server info");
			ChatUtil.sendLocalMessage("§6help§r   > shows this message");
			ChatUtil.sendLocalMessage("§6set§r <moduleName> <value> > set value of module");
			ChatUtil.sendLocalMessage("§6list§r <moduleName> > list modules / values of module");
			break;
			
		case "clear":
			mc.ingameGUI.getChatGUI().clearChatMessages(true);
			break;
			
		case "ip":
			try {
				if (mc.player.isServerWorld()) {
					ChatUtil.sendClientMessage("IP: " + mc.getCurrentServerData().serverIP);
				} else {
					ChatUtil.sendClientMessage("You are not in the server");
				}			
			} catch (Exception e) {
				ChatUtil.sendClientMessage("error im debil");
			}
			break;
			
		case "set":
			try {
				switch (args[1]) {
				case "client":
					switch (args[2]) {
						case "color":
							if (args[3].contains("rainbow")) {
								AllahClient.color = -1;
								ChatUtil.sendClientMessage("Changed client color to " + args[3]);
							} else {
								AllahClient.color = Integer.parseInt(args[3].substring(1), 16);
								AllahClient.acolor = (AllahClient.color & 0x00FFFFFF) | 0xFF000000;
								ChatUtil.sendClientMessage("Changed client color to " + args[3]);
							}
							break;
							
						case "rbdelay":
							AllahClient.rainbowDelay = Integer.parseInt(args[3]);
							ChatUtil.sendClientMessage("Changed rainbow delay to " + args[3]);
							break;
						case "corner":
							AllahClient.corner = Integer.parseInt(args[3]);
							ChatUtil.sendClientMessage("Changed rounded to " + args[3]);
							break;
						default:
							ChatUtil.sendClientMessage("Unknown variable " + args[2]);
							break;
					}
					break;
					
				case "killaura":
					switch (args[2]) {
						case "fov":
							KillAura.fov = Double.parseDouble(args[3]);
							ChatUtil.sendClientMessage("Changed fov to " + args[3]);
							break;
						case "range":
							KillAura.range = Float.parseFloat(args[3]);
							ChatUtil.sendClientMessage("Changed range to " + args[3]);
							break;
						case "timer":
							KillAura.timer = Integer.parseInt(args[3]);
							ChatUtil.sendClientMessage("Changed timer to " + args[3]);
							break;
						default:
							ChatUtil.sendClientMessage("Unknown variable " + args[2]);
							break;
					}
					break;
					
				case "targethud":
					switch (args[2]) {
					case "range":
						TargetHUD.range = Integer.parseInt(args[3]);
						ChatUtil.sendClientMessage("Changed range to " + args[3]);
						break;

					default:
						ChatUtil.sendClientMessage("Unknown variable " + args[2]);
						break;
					}
					break;

				default:
					ChatUtil.sendClientMessage("Unknown module: " + args[1]);
					break;
				}
				
				AllahClient.fileMgr.saveMods();
			} catch (Exception e) {
				ChatUtil.sendClientMessage(e.toString());
			}
			break;

		case "list":
			if (args.length > 1) {
				switch (args[1]) {
				case "client":
					ChatUtil.sendClientMessage("Variables for AllahClient:");
					ChatUtil.sendLocalMessage("");
					if (AllahClient.color == -1) {
						ChatUtil.sendLocalMessage("§6color§r: rainbow");
					} else {
						ChatUtil.sendLocalMessage("§6color§r (HEX): " + String.format("#%06X", (0xFFFFFF & AllahClient.color)));
					}
					ChatUtil.sendLocalMessage("§6rbdelay§r (int): " + AllahClient.rainbowDelay);
					ChatUtil.sendLocalMessage("§6corner§r (int): " + AllahClient.corner);
					break;
					
				case "killaura":
					ChatUtil.sendClientMessage("Variables for KillAura:");
					ChatUtil.sendLocalMessage("");
					ChatUtil.sendLocalMessage("§6fov§r (float): " + KillAura.fov);
					ChatUtil.sendLocalMessage("§6range§r (float): " + KillAura.range);
					break;
					
				case "targethud":
					ChatUtil.sendClientMessage("Variables for Target HUD:");
					ChatUtil.sendLocalMessage("");
					ChatUtil.sendLocalMessage("§6range§r (int): " + TargetHUD.range);
					break;
					
				default:
					ChatUtil.sendClientMessage("Unknown module: " + args[1]);
					break;
				}
			} else {
				ChatUtil.sendClientMessage("Modules: client, killaura, targethud");
			}
			break;
			
		default:
			ChatUtil.sendClientMessage("Unknown command: " + args[0]);
			break;
		}
	}
}
