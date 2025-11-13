package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.WD;
import com.pnfsoftware.jebglobal.hP;
import com.pnfsoftware.jebglobal.lO;
import com.pnfsoftware.jebglobal.oe;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Ser
public class DH extends AbstractBinaryUnit {
   private static final ILogger A = GlobalLog.getLogger(DH.class);
   @SerId(1)
   int pC;

   public DH(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "arsc", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      ByteBuffer var1 = this.getInput().getHeader();
      if (this.pC == 0) {
         this.pC = EndianUtil.swapShort(var1.getShort(0)) & '\uffff';
         if (this.pC != 2 && this.pC != 3) {
            return false;
         }
      }

      try {
         try (InputStream var2 = this.getInput().getStream()) {
            lO var3 = new lO(var2);
            if (this.pC == 2) {
               var3.kS();
            } else {
               var3.wS();
            }

            for (oe var5 : var3.A()) {
               this.logWarn(true, var5.toString());
            }
         }

         return true;
      } catch (IOException var8) {
         A.catching(var8);
         return false;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         if (this.pC == 3) {
            var1.addPresentation(new rQ(this, 1L, S.L("Decoded XML"), true), false);
         } else {
            var1.addPresentation(new zp(this, 1L, S.L("Decoded Table"), true), false);
         }
      }

      return var1;
   }

   private String pC() throws Exception {
      String var6;
      try (InputStream var1 = this.getInput().getStream()) {
         lO var2 = new lO(var1);
         WD var3 = var2.kS();
         vi var4 = new vi(var3);
         var4.A();
         sy var5 = new sy(var4.pC(), false);
         var6 = Strings.trim(var5.pC());
      }

      return var6;
   }

   private String A() throws Exception {
      String var4;
      try (InputStream var1 = this.getInput().getStream()) {
         lO var2 = new lO(var1);
         hP var3 = var2.wS();
         var4 = var3.wS();
      }

      return var4;
   }
}
