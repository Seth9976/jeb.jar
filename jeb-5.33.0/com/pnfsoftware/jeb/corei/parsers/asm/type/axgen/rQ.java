package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.Deserializer;
import com.pnfsoftware.jeb.util.serialization.SerializationManager;
import com.pnfsoftware.jebglobal.ckh;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class rQ {
   private static Map pC = new HashMap();
   private static volatile Sv A = null;

   public static K pC(SubsystemType var0) {
      int var1 = var0.id();
      K var2 = (K)pC.get(var1);
      if (var2 == null) {
         synchronized (rQ.class) {
            if (var2 == null) {
               String var4 = null;
               if (var0 == SubsystemType.LINUX) {
                  var4 = "axdb.glibc.bin";
               } else if (var0 == SubsystemType.WINDOWS || var0 == SubsystemType.WINDOWS_USER) {
                  var4 = "axdb.win32.bin";
               }

               try (InputStream var5 = AssetManager.getAsset(var4)) {
                  ckh var6 = ckh.pC();
                  SerializationManager var7 = new SerializationManager(var6);
                  Deserializer var8 = var7.getDeserializer(var5);
                  var8.deserialize(Ws.class);
                  var2 = (K)var8.deserialize(K.class);
               } catch (Exception var12) {
               }

               if (var2 == null) {
                  var2 = new K(new Ws("empty", null, null), new HashMap());
               }

               pC.put(var1, var2);
            }
         }
      }

      return var2;
   }

   public static Sv pC() {
      if (A == null) {
         synchronized (rQ.class) {
            if (A == null) {
               Sv var1 = new Sv();
               K var2 = pC(SubsystemType.LINUX);
               var1.pC(var2);
               var2 = pC(SubsystemType.WINDOWS);
               var1.pC(var2);
               A = var1;
            }
         }
      }

      return A;
   }
}
