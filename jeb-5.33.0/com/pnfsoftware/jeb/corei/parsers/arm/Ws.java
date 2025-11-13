package com.pnfsoftware.jeb.corei.parsers.arm;

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
import com.pnfsoftware.jebglobal.FX;
import com.pnfsoftware.jebglobal.at;
import com.pnfsoftware.jebglobal.avl;
import com.pnfsoftware.jebglobal.tR;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Ws extends at {
   private static final ILogger A = GlobalLog.getLogger(Ws.class);
   private Map kS = new HashMap();
   private FX wS = null;

   public Ws(INativeCodeUnit var1, String var2, INet var3) {
      super(var1, var2, var3, "ArmDoc");
   }

   @Override
   public TypedContent getItemInformation(IAddressableUnit var1, long var2, String var4, List var5) {
      if (var1 != this.getPrimaryTarget()) {
         return null;
      } else {
         INativeItem var6 = ((INativeCodeUnit)this.getPrimaryTarget()).getItemObject(var2);
         if (var6 instanceof avl) {
            String var7 = ((avl)var6).kS();
            if (var1 instanceof INativeCodeUnit) {
               switch (((INativeCodeUnit)var1).getProcessor().getType().id()) {
                  case 40:
                     return this.A(var7, 32);
                  case 183:
                     return this.A(var7, 64);
                  default:
                     return null;
               }
            }
         }

         return null;
      }
   }

   public TypedContent A(String var1, int var2) {
      this.wS = var2 == 64 ? FX.pC(this) : FX.A(this);
      return this.pC(this.wS, var1, var2);
   }

   private TypedContent pC(FX var1, String var2, int var3) {
      Document var4 = this.A(var1, var2, var3);
      if (var4 == null) {
         return null;
      } else {
         String var5 = this.A(var4);
         return var5 != null ? TypedContent.html(var5) : null;
      }
   }

   @Override
   protected Element pC(String var1, Document var2) {
      try {
         Object var3 = new JSONParser().parse(var1);
         String var4 = this.pC((JSONObject)var3);
         return Jsoup.parse(var4, "");
      } catch (IOException var5) {
         A.catchingSilent(var5);
      } catch (ParseException var6) {
         A.catchingSilent(var6);
      }

      return null;
   }

   @Override
   public TypedContent E(String var1) {
      int var2 = var1.indexOf("?");
      if (var2 > 0) {
         var1 = var1.substring(0, var2);
      }

      Document var3 = this.A(var1);
      if (var3 == null) {
         return null;
      } else {
         String var4 = this.A(var3);
         return var4 != null ? TypedContent.html(var4) : null;
      }
   }

   private Document A(FX var1, String var2, int var3) {
      for (tR var5 : var1) {
         if (var5.kS(var2)) {
            String var6 = var5.pC(var2);
            String var7 = (String)this.kS.get(var6);
            if (var7 != null) {
               return this.A(var7);
            }

            for (String var10 : var5.A(var6)) {
               if (var10 != null) {
                  Document var11 = this.A(var10);
                  if (var11 != null) {
                     this.kS.put(var6, var10);
                     return var11;
                  }
               }
            }
         }
      }

      return null;
   }

   public final Document ys(String var1) {
      return this.A(var1);
   }

   @Override
   protected final Document A(String var1) {
      try {
         String var2 = this.kS(var1);
         if (var2 == null) {
            return null;
         } else {
            Object var3 = new JSONParser().parse(var2);
            String var4 = this.pC((JSONObject)var3);
            return Jsoup.parse(var4, "");
         }
      } catch (IOException var5) {
         return null;
      } catch (ParseException var6) {
         A.catchingSilent(var6);
         return null;
      }
   }

   protected String A(Document var1) {
      for (Element var4 : var1.getElementsByTag("h3")) {
         String var5 = var4.text();
         int var6 = var5.indexOf("(");
         if (var6 > 0) {
            var4.text(var5.substring(0, var6));
         }
      }

      return this.pC(var1.toString(), "/", false, var1);
   }

   private String pC(JSONObject var1) throws IOException {
      Object var2 = var1.get("content");
      return var2 instanceof String ? new String(Base64.decode((String)var2), Charset.forName("UTF-8")) : null;
   }

   @Override
   protected void pC(StringBuilder var1) {
   }

   @Override
   protected String[] pC() {
      return new String[0];
   }
}
