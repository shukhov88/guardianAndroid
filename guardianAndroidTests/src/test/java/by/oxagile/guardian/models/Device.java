package by.oxagile.guardian.models;


public class Device {

    String owner;
    String model;
    String name;
    String platform;
    String platformVersion;
    String id;
    String type;
    int screenFactor;
    String mac;
    String invent;

    public String getOwner() {
        return owner;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public String getID() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getScreenFactor() {
        return screenFactor;
    }

    public String getMac() {
        return mac;
    }

    public String getInvent() {
        return invent;
    }

    @Override
    public String toString() {
        return "Device{" +
                "owner='" + owner + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", platform='" + platform + '\'' +
                ", platformVersion='" + platformVersion + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", screenFactor=" + screenFactor +
                ", mac='" + mac + '\'' +
                ", invent='" + invent + '\'' +
                '}';
    }
}
