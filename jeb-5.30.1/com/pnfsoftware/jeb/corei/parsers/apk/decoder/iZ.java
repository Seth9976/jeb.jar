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
import com.pnfsoftware.jebglobal.IO;
import com.pnfsoftware.jebglobal.Kd;
import com.pnfsoftware.jebglobal.kO;
import com.pnfsoftware.jebglobal.xn;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@Ser
public class iZ extends AbstractBinaryUnit {
   private static final ILogger RF = GlobalLog.getLogger(iZ.class);
   @SerId(1)
   int q;

   public iZ(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "arsc", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      ByteBuffer var1 = this.getInput().getHeader();
      if (this.q == 0) {
         this.q = EndianUtil.swapShort(var1.getShort(0)) & '\uffff';
         if (this.q != 2 && this.q != 3) {
            return false;
         }
      }

      try {
         try (InputStream var2 = this.getInput().getStream()) {
            kO var3 = new kO(var2);
            if (this.q == 2) {
               var3.Dw();
            } else {
               var3.Uv();
            }

            for (xn var5 : var3.RF()) {
               this.logWarn(true, var5.toString());
            }
         }

         return true;
      } catch (IOException var8) {
         RF.catching(var8);
         return false;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         if (this.q == 3) {
            var1.addPresentation(new tw(this, 1L, S.L("Decoded XML"), true), false);
         } else {
            var1.addPresentation(new EE(this, 1L, S.L("Decoded Table"), true), false);
         }
      }

      return var1;
   }

   private String q() throws Exception {
      String var6;
      try (InputStream var1 = this.getInput().getStream()) {
         kO var2 = new kO(var1);
         Kd var3 = var2.Dw();
         bK var4 = new bK(var3);
         var4.xK();
         vb var5 = new vb(var4.RF(), false);
         var6 = Strings.trim(var5.q());
      }

      return var6;
   }

   private String RF() throws Exception {
      String var4;
      try (InputStream var1 = this.getInput().getStream()) {
         kO var2 = new kO(var1);
         IO var3 = var2.Uv();
         var4 = var3.Dw();
      }

      return var4;
   }
}
