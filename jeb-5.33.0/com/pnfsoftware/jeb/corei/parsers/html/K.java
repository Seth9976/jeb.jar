package com.pnfsoftware.jeb.corei.parsers.html;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

@Ser
public class K extends AbstractBinaryUnit {
   @SerTransient
   private Document pC;
   @SerTransient
   private String A;

   public K(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("text/html", var2, "html", var1, var3, var4, var5);
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && this.pC != null;
   }

   private int A() {
      try {
         int var2;
         try (InputStream var1 = this.getInput().getStream()) {
            var2 = Strings.getInitialBlankSize(var1, true, '\f');
         }

         return var2;
      } catch (IOException var6) {
         return 0;
      }
   }

   @Override
   protected boolean processInternal() {
      int var2 = this.A();
      ByteBuffer var3 = this.getInput().getHeader();
      byte[] var4 = new byte[var3.limit()];
      var3.get(var4);
      if (var2 >= var4.length) {
         try (InputStream var5 = this.getInput().getStream()) {
            var4 = IO.readInputStream(var5);
         } catch (IOException var17) {
            return false;
         }
      }

      int var24 = var4[var2];
      if (var24 != 60) {
         return false;
      } else {
         boolean var1 = HtmlIdentifier.checkBytes(var4, var2 + 1, false, 63, 120, 109, 108);
         if (!var1) {
            var1 = HtmlIdentifier.checkBytes(var4, var2 + 1, false, 115, 118, 103);
         }

         try (InputStream var22 = this.getInput().getStream()) {
            byte[] var25 = IO.readInputStream(var22);
            String var6 = Strings.readBOM(var25);
            if (var6 == null) {
               var6 = "UTF-8";
            }

            try {
               var21 = new String(var25, var2, var25.length - var2, var6);
            } catch (UnsupportedEncodingException var13) {
               var21 = new String(var25, var2, var25.length - var2, "UTF-8");
            }
         } catch (Exception var20) {
            return false;
         }

         Parser var23 = var1 ? Parser.xmlParser() : Parser.htmlParser();

         try {
            this.pC = Jsoup.parse(var21, "", var23);
         } catch (Exception var18) {
            if (!var1) {
               return false;
            }
         }

         if (this.pC == null || var1 && !this.pC.getElementsByTag("html").isEmpty()) {
            try (InputStream var26 = this.getInput().getStream()) {
               this.pC = Jsoup.parse(var21);
            } catch (Exception var15) {
               return false;
            }
         }

         if (!this.hasChildren()) {
            var24 = 0;

            for (Element var7 : this.pC.getElementsByTag("script")) {
               byte[] var8 = Strings.encodeUTF8(var7.data());
               IUnit var9 = this.getUnitProcessor().process("script #" + var24, new BytesInput(var8), this, null, true);
               if (var9 != null) {
                  this.addChild(var9);
               }

               var24++;
            }
         }

         return true;
      }
   }

   public Document pC() {
      return this.pC;
   }

   @Override
   public String getFormatType() {
      if (this.A != null) {
         return this.A;
      } else if (this.pC == null) {
         return super.getFormatType();
      } else {
         Elements var1 = this.pC.children();
         if (var1.size() == 1) {
            this.A = "svg".equalsIgnoreCase(((Element)var1.get(0)).tagName()) ? "svg" : super.getFormatType();
         } else {
            this.A = super.getFormatType();
         }

         return this.A;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new Ws(this, 1L, S.L("Formatted Text"), true), false);
      }

      return var1;
   }
}
