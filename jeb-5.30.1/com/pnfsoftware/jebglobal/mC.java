package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.IAddressableUnit;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.base.TypedContent;
import com.pnfsoftware.jeb.util.encoding.Base64;
import com.pnfsoftware.jeb.util.encoding.json.parser.JSONParser;
import com.pnfsoftware.jeb.util.encoding.json.parser.ParseException;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.INet;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class mC extends Uz {
   private static final ILogger RF = GlobalLog.getLogger(mC.class);
   private Map xK = new HashMap();
   private lv Dw = null;

   public mC(INativeCodeUnit var1, String var2, INet var3) {
      super(var1, var2, var3, "ArmDoc");
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (var1 != this.getPrimaryTarget()) {
         return null;
      } else {
         INativeItem var6 = ((INativeCodeUnit)this.getPrimaryTarget()).getItemObject(var2);
         if (var6 instanceof ayh) {
            String var7 = ((ayh)var6).xK();
            if (var1 instanceof INativeCodeUnit) {
               switch (((INativeCodeUnit)var1).getProcessor().getType().id()) {
                  case 40:
                     return this.RF(var7, 32);
                  case 183:
                     return this.RF(var7, 64);
                  default:
                     return null;
               }
            }
         }

         return null;
      }
   }

   public TypedContent RF(String var1, int var2) {
      this.Dw = var2 == 64 ? lv.q(this) : lv.RF(this);
      return this.q(this.Dw, var1, var2);
   }

   private TypedContent q(lv var1, String var2, int var3) {
      Document var4 = this.RF(var1, var2, var3);
      if (var4 == null) {
         return null;
      } else {
         String var5 = this.RF(var4);
         return var5 != null ? TypedContent.html(var5) : null;
      }
   }

   @Override
   protected Element q(String var1, Document var2) {
      try {
         Object var3 = new JSONParser().parse(var1);
         String var4 = this.q((JSONObject)var3);
         return Jsoup.parse(var4, "");
      } catch (IOException var5) {
         RF.catchingSilent(var5);
      } catch (ParseException var6) {
         RF.catchingSilent(var6);
      }

      return null;
   }

   @Override
   public TypedContent oW(String var1) {
      int var2 = var1.indexOf("?");
      if (var2 > 0) {
         var1 = var1.substring(0, var2);
      }

      Document var3 = this.RF(var1);
      if (var3 == null) {
         return null;
      } else {
         String var4 = this.RF(var3);
         return var4 != null ? TypedContent.html(var4) : null;
      }
   }

   private Document RF(lv var1, String var2, int var3) {
      for (Mt var5 : var1) {
         if (var5.xK(var2)) {
            String var6 = var5.q(var2);
            String var7 = (String)this.xK.get(var6);
            if (var7 != null) {
               return this.RF(var7);
            }

            for (String var10 : var5.RF(var6)) {
               if (var10 != null) {
                  Document var11 = this.RF(var10);
                  if (var11 != null) {
                     this.xK.put(var6, var10);
                     return var11;
                  }
               }
            }
         }
      }

      return null;
   }

   public final Document nf(String var1) {
      return this.RF(var1);
   }

   @Override
   protected final Document RF(String var1) {
      try {
         String var2 = this.xK(var1);
         if (var2 == null) {
            return null;
         } else {
            Object var3 = new JSONParser().parse(var2);
            String var4 = this.q((JSONObject)var3);
            return Jsoup.parse(var4, "");
         }
      } catch (IOException var5) {
         return null;
      } catch (ParseException var6) {
         RF.catchingSilent(var6);
         return null;
      }
   }

   protected String RF(Document var1) {
      for (Element var4 : var1.getElementsByTag("h3")) {
         String var5 = var4.text();
         int var6 = var5.indexOf("(");
         if (var6 > 0) {
            var4.text(var5.substring(0, var6));
         }
      }

      return this.q(var1.toString(), "/", false, var1);
   }

   private String q(JSONObject var1) throws IOException {
      Object var2 = var1.get("content");
      return var2 instanceof String ? new String(Base64.decode((String)var2), Charset.forName("UTF-8")) : null;
   }

   @Override
   protected void q(StringBuilder var1) {
   }

   @Override
   protected String[] RF() {
      return new String[0];
   }
}
