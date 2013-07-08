package my.think.proxy.sample;

import static my.think.proxy.sample.Country.Others;

class Material {

    public String bread() {
        return "Bread|";
    }

    public String lettuce() {
        return "Lettuce|";
    }

    public String cutlet() {
        return "Meat|";
    }

    @Location(Others)
    public String sauce() {
        return "TomatoSauce|";
    }
}
