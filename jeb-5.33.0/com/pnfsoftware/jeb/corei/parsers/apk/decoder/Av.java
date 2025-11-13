package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jebglobal.ckx;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Av {
   private static final ILogger pC = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.class);
   private static volatile com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av A;
   private static volatile com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av kS;
   private Map wS = new HashMap();

   public static synchronized com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av pC() {
      if (A == null) {
         synchronized (com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.class) {
            if (A == null) {
               A = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av(
                  ckx.pC(new byte[]{99, 15, 10, 22, 29, 6, 13, 73, 76, 21, 0, 6, 1, 44, 50, 12, 15, 7, 15, 3, 22, 7, 90, 86, 21, 1}, 1, 2)
               );
            }
         }
      }

      return A;
   }

   public static synchronized com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av A() {
      if (kS == null) {
         synchronized (com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.class) {
            if (kS == null) {
               kS = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av(
                  ckx.pC(new byte[]{34, 1, 20, 11, 29, 0, 3, 69, 21, 84, 92, 17, 90, 14, 73, 84, 85}, 2, 208)
               );
            }
         }
      }

      return kS;
   }

   private Av(String var1) {
      try (InputStream var2 = AssetManager.getAsset(var1)) {
         DocumentBuilderFactory var9 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var4 = var9.newDocumentBuilder();
         Document var5 = var4.parse(var2);
         this.pC(var5);
      } catch (Exception var8) {
         pC.catchingSilent(var8);
         RuntimeException var3 = new RuntimeException("Error parsing Android Framework xml attr file", var8);
         if (Licensing.isDebugBuild()) {
            throw var3;
         }

         JebCoreService.notifySilentExceptionToClient(var3);
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av pC(String var1) {
      return (com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av)this.wS.get(var1);
   }

   private void pC(Document var1) {
      NodeList var2 = var1.getElementsByTagName("attr");

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         if (var2.item(var3) instanceof Element var5) {
            this.pC(var5);
         }
      }
   }

   private void pC(Element var1) {
      String var2 = var1.getAttribute("name");
      if (!"".equals(var2)) {
         byte var3 = 0;
         NodeList var4 = var1.getElementsByTagName("flag");
         if (var4.getLength() == 0) {
            var3 = 1;
            var4 = var1.getElementsByTagName("enum");
            if (var4.getLength() == 0) {
               if (!this.wS.containsKey(var2)) {
                  this.wS.put(var2, com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av.pC);
               }

               return;
            }
         }

         com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av var5 = null;

         for (int var6 = 0; var6 < var4.getLength(); var6++) {
            Node var7 = var4.item(var6);
            Element var8 = (Element)var7;
            String var9 = var8.getAttribute("value");
            Long var10 = Conversion.stringToLongUnsafe(var9);
            if (var10 != null) {
               if (var5 == null) {
                  var5 = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av(var3);
                  com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av var10000 = (com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av)this.wS.put(var2, var5);
               }

               String var11 = var8.getAttribute("name");
               String var12 = (String)var5.kS.put(var10, var11);
               if (var10 == 0L && var12 != null) {
                  var5.kS.put(var10, var12);
               }
            }
         }

         if (var5 != null && var5.A() && !pC(var5.kS.keySet())) {
            ArrayList var13 = new ArrayList(var5.kS.keySet());
            var13.sort((var0, var1x) -> -Long.compareUnsigned(MathUtil.bitcount(var0), MathUtil.bitcount(var1x)));
            LinkedHashMap var14 = new LinkedHashMap(var5.kS.size());

            for (Long var16 : var13) {
               var14.put(var16, (String)var5.kS.get(var16));
            }

            var5.kS = var14;
         }
      }
   }

   public static boolean pC(Set var0) {
      long var1 = 0L;

      for (long var4 : var0) {
         if ((var1 & var4) != 0L) {
            return false;
         }

         var1 |= var4;
      }

      return var1 != 0L;
   }

   public static class Av {
      public static final com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av pC = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.Av.Av(-1);
      private int A;
      private Map kS = new HashMap();

      Av(int var1) {
         this.A = var1;
      }

      public int pC() {
         return this.A;
      }

      public boolean A() {
         return this.A == 0;
      }

      public Map kS() {
         return Collections.unmodifiableMap(this.kS);
      }

      public boolean wS() {
         return this.kS.isEmpty();
      }
   }
}
