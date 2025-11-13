package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ApkStringResHelper {
   private static final ILogger logger = GlobalLog.getLogger(ApkStringResHelper.class);
   private IApkUnit apk;
   private List preferredLocales;
   private volatile ApkStringResHelper.InternalMap resIdMap;

   public ApkStringResHelper(IApkUnit var1, List var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.apk = var1;
         this.preferredLocales = var2;
      }
   }

   public ApkStringResHelper(IApkUnit var1) {
      this(var1, null);
   }

   private ApkStringResHelper.InternalMap generateResIdMap() {
      ApkStringResHelper.InternalMap var1 = new ApkStringResHelper.InternalMap();
      if (UnitUtil.retrieveProcessed(true, this.apk, "Resources", "values", "public.xml") instanceof IXmlUnit var3) {
         NodeList var4 = var3.getDocument().getElementsByTagName("public");

         for (int var5 = 0; var5 < var4.getLength(); var5++) {
            Element var6 = (Element)var4.item(var5);
            int var7 = Conversion.stringToInt(var6.getAttribute("id"));
            if (var7 != 0) {
               String var8 = var6.getAttribute("name");
               String var9 = var6.getAttribute("type");
               var1.pubmap.put(var7, new Couple(var9, var8));
            }
         }
      }

      IUnit var10 = null;
      if (this.preferredLocales != null) {
         for (String var13 : this.preferredLocales) {
            var10 = UnitUtil.retrieveProcessed(true, this.apk, "Resources", "values-" + var13, "strings.xml");
            if (var10 != null) {
               break;
            }
         }
      }

      if (var10 == null) {
         var10 = UnitUtil.retrieveProcessed(true, this.apk, "Resources", "values", "strings.xml");
      }

      if (var10 instanceof IXmlUnit var12) {
         NodeList var14 = var12.getDocument().getElementsByTagName("string");

         for (int var15 = 0; var15 < var14.getLength(); var15++) {
            Element var16 = (Element)var14.item(var15);
            String var17 = var16.getAttribute("name");
            String var18 = var16.getTextContent();
            var1.strmap.put(var17, var18);
         }
      }

      return var1;
   }

   private void init() {
      if (this.resIdMap == null) {
         synchronized (this) {
            if (this.resIdMap == null) {
               try {
                  ApkStringResHelper.InternalMap var2 = this.generateResIdMap();
                  this.resIdMap = var2;
               } catch (Exception var4) {
                  JebCoreService.silentExcept(var4);
                  this.resIdMap = new ApkStringResHelper.InternalMap();
               }
            }
         }
      }
   }

   public String getDefaultString(int var1) {
      this.init();
      Couple var2 = (Couple)this.resIdMap.pubmap.get(var1);
      if (var2 == null) {
         return null;
      } else {
         return !"string".equals(var2.getFirst()) ? null : (String)this.resIdMap.strmap.get(var2.getSecond());
      }
   }

   public String generateAutoCommentForPotentialResourceId(int var1) {
      this.init();
      Couple var2 = (Couple)this.resIdMap.pubmap.get(var1);
      if (var2 == null) {
         return null;
      } else {
         String var3 = (String)var2.getFirst() + ":" + (String)var2.getSecond();
         if ("string".equals(var2.getFirst())) {
            String var4 = (String)this.resIdMap.strmap.get(var2.getSecond());
            if (var4 != null) {
               var3 = var3 + " \"" + Formatter.escapeString(var4) + "\"";
            }
         }

         return var3;
      }
   }

   public static class InternalMap {
      public Map pubmap = new HashMap();
      public Map strmap = new HashMap();
   }
}
