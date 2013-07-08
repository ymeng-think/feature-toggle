package my.think.aspect.runner.sample;

import my.think.aspect.runner.ToggleRunner;

import static my.think.aspect.runner.sample.Country.Others;

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

    @ToggleRunner(LocationDependingRunner.class)
    @Location(Others)
    public String sauce() {
        return "TomatoSauce|";
    }
}
