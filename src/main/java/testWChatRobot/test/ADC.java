package testWChatRobot.test;


import lombok.Data;

@Data
public class ADC extends AD{
    public int attack;

    //public String ability;

    public int distance;

    public ADC(int attack, String ability, int distance){
        this.attack = attack;
        super.ability = ability;
        //this.ability = ability;
        this.distance = distance;
    }

    public static void main(String[] args) {
        AD ad = new AD(3, "wu");
        System.out.println(ad.getAbility());

        AD adc = new ADC(5, "li", 1);
        System.out.println(adc.getAbility());

        System.out.println(ad.getAbility());
    }

}
