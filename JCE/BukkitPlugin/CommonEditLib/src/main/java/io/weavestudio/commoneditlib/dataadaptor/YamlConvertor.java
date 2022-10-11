package io.weavestudio.commoneditlib.dataadaptor;

import org.bukkit.configuration.file.YamlConfiguration;

import static io.weavestudio.commoneditlib.dataadaptor.DataAdaptorUtils.fromObject;

public class YamlConvertor {

    public static DataAdaptor fromYaml(YamlConfiguration yamlConfig) {
        return fromObject(yamlConfig);
    }

    public static YamlConfiguration toYaml(DataAdaptor data) {
        YamlConfiguration yamlConfig = new YamlConfiguration();
        for (String key : data.keys()) {
            DataAdaptor value = data.get(key);
            yamlConfig.set(key, value.asPrimitive());
        }
        return yamlConfig;
    }

}
