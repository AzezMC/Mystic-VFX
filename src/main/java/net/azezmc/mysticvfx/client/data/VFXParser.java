package net.azezmc.mysticvfx.client.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.azezmc.mysticvfx.client.utils.ColorCurve;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

public class VFXParser {
    private static final Gson GSON = new Gson();

    public static JsonObject loadVFXData(Identifier id) {
        try {
            // Đọc file từ: assets/mysticvfx/vfx_data/tên_file.json
            Identifier filePath = new Identifier(id.getNamespace(), "vfx_data/" + id.getPath() + ".json");
            Optional<Resource> resource = MinecraftClient.getInstance().getResourceManager().getResource(filePath);

            if (resource.isPresent()) {
                Reader reader = new InputStreamReader(resource.get().getInputStream());
                return JsonParser.parseReader(reader).getAsJsonObject();
            }
        } catch (Exception e) {
            System.err.println("[MysticVFX] Lỗi khi đọc file VFX: " + id.toString());
            e.printStackTrace();
        }
        return new JsonObject();
    }

    public static ColorCurve parseColorCurve(JsonObject json, String key) {
        ColorCurve curve = new ColorCurve();
        if (json.has(key)) {
            JsonObject curveObj = json.getAsJsonObject(key);
            curveObj.entrySet().forEach(entry -> {
                float time = Float.parseFloat(entry.getKey());
                var arr = entry.getValue().getAsJsonArray();
                curve.add(time, arr.get(0).getAsFloat(), arr.get(1).getAsFloat(), arr.get(2).getAsFloat(), arr.get(3).getAsFloat());
            });
        }
        return curve;
    }
}