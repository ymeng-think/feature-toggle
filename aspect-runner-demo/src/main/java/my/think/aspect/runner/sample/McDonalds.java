package my.think.aspect.runner.sample;

public class McDonalds {

    private Country country;

    public McDonalds(Country country) {
        this.country = country;
    }

    public String makeHamburg() {
        StringBuilder desc = new StringBuilder();

        Material material = new Material();
        desc.append(material.bread());
        desc.append(material.sauce(country));
        desc.append(material.lettuce());
        desc.append(material.cutlet());
        desc.append(material.bread());

        return desc.toString();
    }
}
