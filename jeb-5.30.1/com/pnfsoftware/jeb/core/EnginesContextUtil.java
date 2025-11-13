package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.serialization.SerializationException;
import java.io.File;
import java.io.IOException;

public class EnginesContextUtil {
   public static IRuntimeProject getMainProject(IEnginesContext var0) {
      return (IRuntimeProject)Lists.getFirst(var0.getProjects());
   }

   public static IRuntimeProject createNewProjectFromFile(IEnginesContext var0, File var1) throws IOException {
      IRuntimeProject var2;
      try {
         var2 = var0.loadProject(var1.getName());
      } catch (SerializationException var4) {
         throw new RuntimeException(var4);
      }

      Artifact var3 = new Artifact(var1.getName(), new FileInput(var1));
      var2.processArtifact(var3);
      return var2;
   }
}
