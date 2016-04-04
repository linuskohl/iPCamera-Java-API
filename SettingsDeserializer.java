package com.munichtrading.tracking.camera;

/**
 * JSON Deserializer for the Settings Class
 * 
 * @author Linus Kohl
 * @version 0.1
 *
 * Copyright (C) 2016 Linus Kohl, kohl@munichtrading.com
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD license.  See the LICENSE file for details.
 */

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SettingsDeserializer implements JsonDeserializer<Settings> {

  public Settings deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
      throws JsonParseException {

    final JsonObject jsonObject = json.getAsJsonObject();
    final Settings settings = new Settings();

    settings.fps        = jsonObject.get("fps").getAsInt();
    settings.focus      = (jsonObject.get("focus").getAsInt() != 0);
    settings.flip       = jsonObject.get("flip").getAsInt();
    settings.resolution = jsonObject.get("resolution").getAsInt();
    settings.torch      = (jsonObject.get("torch").getAsInt() != 0);
    settings.stats      = jsonObject.get("stats").getAsBoolean();
    settings.camera     = jsonObject.get("camera").getAsInt();
    settings.quality    = jsonObject.get("quality").getAsDouble();
    settings.wb         = (jsonObject.get("wb").getAsInt() != 0);
    settings.rotation   = jsonObject.get("rotation").getAsInt();
    settings.exposure   = (jsonObject.get("exposure").getAsInt() != 0);

    // set battery as integer containing the percentage value
    final JsonElement battery    = jsonObject.get("battery");
    String int_string = battery.getAsString().replaceAll("[^\\d]", "");
    settings.battery  = Integer.parseInt(int_string);
    return settings;
  }
  
}