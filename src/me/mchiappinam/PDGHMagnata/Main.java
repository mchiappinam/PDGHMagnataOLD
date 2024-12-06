package me.mchiappinam.pdghmagnata;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.iCo6.system.Account;
import com.iCo6.system.Accounts;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

public class Main extends JavaPlugin implements Listener {
    private Accounts acc = null;
    private int task = 0;
	boolean jaDivulgou = false;
    
	String Magnata;

	public void onEnable() {
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}catch(Exception e) {}
		}
		getServer().getPluginManager().registerEvents(this, this);
		
		verificar();
	    
	    /**if (!getConfig().contains("magnataAtual")) {
	      //OfflinePlayer random = getServer().getOfflinePlayers()[new java.util.Random().nextInt(getServer().getOfflinePlayers().length)];
	      //Magnata = random.getName();
	      getConfig().set("magnataAtual", Magnata);
	      saveConfig();
	    }else{
	      Magnata = getConfig().getString("magnataAtual");
	    }
	    getConfig().set("magnataAtual", accounts.getTopAccounts(1));
	    saveConfig();
	    Magnata = getConfig().getString("magnataAtual");
	    Magnata2 = getConfig().getString("magnataAtual");
	    Verificar2();*/
	    //List<Account> top = accounts.getTopAccounts(1);
	
	    /**List<Account> top = Accounts.getTopAccounts(5);
	    for (int i = 0; i < top.size(); i++) {
	        Account account = top.get(i);
	        Template.add("i", i + 1);
	        Template.add("name", account.name);
	        Template.add("amount", account.getHoldings().toString());
	        Messaging.send(getServer().getConsoleSender(), Template.parse());
	    }*/
		getServer().getConsoleSender().sendMessage("§3[PDGHMagnata] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHMagnata] §2Acesse: http://pdgh.com.br/");
	}

	public void onDisable() {
		reloadConfig();
		getConfig().set("magnataAtual", Magnata);
		saveConfig();
	}

	private void verificar() {
	    task = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	    	public void run() {
	    		getServer().broadcastMessage("Magnata="+getMagnata());
	    	}
	    }, 0L, 30*20);
	}
	
	public String getMagnata() {
	    String line;
		try {
			List contas = acc.getTopAccounts(5);
			line = contas.iterator();
			if (line.hasNext()) {
				Account conta = (Account)line.next();
				return conta.name;
			}
		} catch (Exception localException3) {}
		return null;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if ((e.getPlayer().getName().equals(Magnata)) && (jaDivulgou)) {
			getServer().broadcastMessage(" ");
			getServer().broadcastMessage("§2§l[Magnata] §a§l"+e.getPlayer().getName()+" §alogou-se no servidor.");
			getServer().broadcastMessage(" ");
		}
	}

  @EventHandler
  public void onChat(ChatMessageEvent e) {
    if ((e.getTags().contains("magnata")) && (e.getSender().getName().equals(Magnata))) {
      e.setTagValue("magnata", "§2[Magnata]");
    }
  }
}