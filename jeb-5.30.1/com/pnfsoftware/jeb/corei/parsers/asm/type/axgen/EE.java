package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jebglobal.cuu;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EE {
   private static Map q = new HashMap();
   private static volatile nI RF = null;

   public static ej q(SubsystemType var0) {
      int var1 = var0.id();
      ej var2 = (ej)q.get(var1);
      if (var2 == null) {
         synchronized (EE.class) {
            if (var2 == null) {
               String var4 = null;
               if (var0 == SubsystemType.LINUX) {
                  var4 = "axdb.glibc.bin";
               } else if (var0 == SubsystemType.WINDOWS || var0 == SubsystemType.WINDOWS_USER) {
                  var4 = "axdb.win32.bin";
               }

               try (InputStream var5 = AssetManager.getAsset(var4)) {
                  cuu var6 = cuu.q();
                  SerializationManager var7 = new SerializationManager(var6);
                  Deserializer var8 = var7.getDeserializer(var5);
                  var8.deserialize(oM.class);
                  var2 = (ej)var8.deserialize(ej.class);
               } catch (Exception var12) {
               }

               if (var2 == null) {
                  var2 = new ej(new oM("empty", null, null), new HashMap());
               }

               q.put(var1, var2);
            }
         }
      }

      return var2;
   }

   public static nI q() {
      if (RF == null) {
         synchronized (EE.class) {
            if (RF == null) {
               nI var1 = new nI();
               ej var2 = q(SubsystemType.LINUX);
               var1.q(var2);
               var2 = q(SubsystemType.WINDOWS);
               var1.q(var2);
               RF = var1;
            }
         }
      }

      return RF;
   }
}
