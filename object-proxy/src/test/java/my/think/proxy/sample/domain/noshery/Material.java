package my.think.proxy.sample.domain.noshery;

import static my.think.proxy.sample.domain.noshery.Country.Others;

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
