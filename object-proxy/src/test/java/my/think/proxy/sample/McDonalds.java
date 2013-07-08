package my.think.proxy.sample;

import static my.think.proxy.sample.Area.area;

public class McDonalds {

    private Country country;

    public McDonalds(Country country) {
        this.country = country;
    }

    public String makeHamburg() {
        StringBuilder desc = new StringBuilder();

        Material material = area(country).create(Material.class);
        desc.append(material.bread());
        desc.append(material.sauce());
        desc.append(material.lettuce());
        desc.append(material.cutlet());
        desc.append(material.bread());

        return desc.toString();
    }
}
