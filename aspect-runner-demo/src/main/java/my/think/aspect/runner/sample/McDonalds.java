package my.think.aspect.runner.sample;

public class McDonalds {

    public String makeHamburg() {
        StringBuilder desc = new StringBuilder();

        Material material = new Material();
        desc.append(material.bread());
        desc.append(material.sauce());
        desc.append(material.lettuce());
        desc.append(material.cutlet());
        desc.append(material.bread());

        return desc.toString();
    }
}
