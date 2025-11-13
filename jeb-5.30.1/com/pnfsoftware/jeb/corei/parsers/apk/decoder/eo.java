package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jebglobal.cvm;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class eo {
   private static final ILogger q = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.class);
   private static volatile com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo RF;
   private static volatile com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo xK;
   private Map Dw = new HashMap();

   public static synchronized com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo q() {
      if (RF == null) {
         synchronized (com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.class) {
            if (RF == null) {
               RF = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo(
                  cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 69, 21, 84, 92, 17, 90, 127, 92, 88, 87, 90, 74, 69, 65, 68, 28, 76, 65, 76}, 2, 134)
               );
            }
         }
      }

      return RF;
   }

   public static synchronized com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo RF() {
      if (xK == null) {
         synchronized (com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.class) {
            if (xK == null) {
               xK = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo(
                  cvm.q(new byte[]{-73, 15, 10, 22, 29, 6, 13, 73, 76, 21, 0, 6, 1, 93, 86, 21, 1}, 1, 214)
               );
            }
         }
      }

      return xK;
   }

   private eo(String var1) {
      try (InputStream var2 = AssetManager.getAsset(var1)) {
         DocumentBuilderFactory var9 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var4 = var9.newDocumentBuilder();
         Document var5 = var4.parse(var2);
         this.q(var5);
      } catch (Exception var8) {
         q.catchingSilent(var8);
         RuntimeException var3 = new RuntimeException("Error parsing Android Framework xml attr file", var8);
         if (Licensing.isDebugBuild()) {
            throw var3;
         }

         JebCoreService.notifySilentExceptionToClient(var3);
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo q(String var1) {
      return (com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo)this.Dw.get(var1);
   }

   private void q(Document var1) {
      NodeList var2 = var1.getElementsByTagName("attr");

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         if (var2.item(var3) instanceof Element var5) {
            this.q(var5);
         }
      }
   }

   private void q(Element var1) {
      String var2 = var1.getAttribute("name");
      if (!"".equals(var2)) {
         byte var3 = 0;
         NodeList var4 = var1.getElementsByTagName("flag");
         if (var4.getLength() == 0) {
            var3 = 1;
            var4 = var1.getElementsByTagName("enum");
            if (var4.getLength() == 0) {
               if (!this.Dw.containsKey(var2)) {
                  this.Dw.put(var2, com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo.q);
               }

               return;
            }
         }

         com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo var5 = null;

         for (int var6 = 0; var6 < var4.getLength(); var6++) {
            Node var7 = var4.item(var6);
            Element var8 = (Element)var7;
            String var9 = var8.getAttribute("value");
            Long var10 = Conversion.stringToLongUnsafe(var9);
            if (var10 != null) {
               if (var5 == null) {
                  var5 = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo(var3);
                  com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo var10000 = (com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo)this.Dw.put(var2, var5);
               }

               String var11 = var8.getAttribute("name");
               String var12 = (String)var5.xK.put(var10, var11);
               if (var10 == 0L && var12 != null) {
                  var5.xK.put(var10, var12);
               }
            }
         }

         if (var5 != null && var5.RF() && !q(var5.xK.keySet())) {
            ArrayList var13 = new ArrayList(var5.xK.keySet());
            var13.sort((var0, var1x) -> -Long.compareUnsigned(MathUtil.bitcount(var0), MathUtil.bitcount(var1x)));
            LinkedHashMap var14 = new LinkedHashMap(var5.xK.size());

            for (Long var16 : var13) {
               var14.put(var16, (String)var5.xK.get(var16));
            }

            var5.xK = var14;
         }
      }
   }

   public static boolean q(Set var0) {
      long var1 = 0L;

      for (long var4 : var0) {
         if ((var1 & var4) != 0L) {
            return false;
         }

         var1 |= var4;
      }

      return var1 != 0L;
   }

   public static int[] q(SortedSet var0, int var1, int var2) {
      Assert.a(var1 >= 0);
      Assert.a(var2 >= 1);
      Integer var3 = null;
      Integer var4 = null;
      int var5 = var1;
      int var6 = var2;

      while (var5 < 64 && var5 + var6 <= 64) {
         boolean var7 = true;
         boolean var8 = true;
         long var9 = 0L;
         long var11 = (1L << var6) - 1L << var5;

         for (long var14 : var0) {
            if ((var14 & var11) != 0L) {
               if ((var14 & ~var11) != 0L) {
                  var7 = false;
                  break;
               }

               if ((var9 & var14) != 0L) {
                  var8 = false;
                  var7 = false;
                  break;
               }

               var9 |= var14;
            }
         }

         if (var7 && var9 != 0L) {
            var3 = var5;
            var4 = var6;
            var6 += var2;
         } else {
            if (var3 != null) {
               break;
            }

            if (var8) {
               var6 += var2;
            } else {
               var5 += var2;
               var6 = var2;
            }
         }
      }

      return var3 == null ? null : new int[]{var3, var3 + var4};
   }

   public static class eo {
      public static final com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo q = new com.pnfsoftware.jeb.corei.parsers.apk.decoder.eo.eo(-1);
      private int RF;
      private Map xK = new HashMap();

      eo(int var1) {
         this.RF = var1;
      }

      public int q() {
         return this.RF;
      }

      public boolean RF() {
         return this.RF == 0;
      }

      public boolean xK() {
         return this.RF == 1;
      }

      public Map Dw() {
         return Collections.unmodifiableMap(this.xK);
      }

      public boolean Uv() {
         return this.xK.isEmpty();
      }
   }
}
