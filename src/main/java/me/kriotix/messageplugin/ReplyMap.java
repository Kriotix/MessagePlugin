


package me.kriotix.messageplugin;

import org.bukkit.entity.Player;
import java.util.HashMap;

public class ReplyMap {
    private HashMap<Player, Player> replyMap = new HashMap<>();

    private static final ReplyMap instance = new ReplyMap();

    public static ReplyMap getInstance() {
        return instance;
    }

    public HashMap<Player, Player> getReplyMap(){
        return replyMap;
    }

    public void updateReplyMap(Player sender, Player receiver) {
        replyMap.put(sender, receiver);
    }
}