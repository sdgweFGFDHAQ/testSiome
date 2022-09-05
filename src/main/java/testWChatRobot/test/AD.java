package testWChatRobot.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AD {
    public int attack;

    public String ability;

    public AD(int attack, String ability){
        this.attack = attack;
        this.ability = ability;
    }
}
