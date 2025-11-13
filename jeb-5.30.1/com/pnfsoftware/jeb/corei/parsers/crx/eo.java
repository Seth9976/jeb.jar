package com.pnfsoftware.jeb.corei.parsers.crx;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.SubInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.corei.parsers.zip.CU;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;

@Ser
public class eo extends AbstractBinaryUnit {
   private static final ILogger Dw = GlobalLog.getLogger(eo.class);
   @SerId(1)
   int q;
   @SerId(2)
   byte[] RF;
   @SerId(3)
   byte[] xK;

   public eo(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "crx", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      int var1;
      try {
         LEDataInputStream var2 = new LEDataInputStream(this.getInput().getStream());

         int var4;
         int var5;
         label71: {
            label70: {
               label69: {
                  boolean var15;
                  label68: {
                     try {
                        int var3 = var2.readInt();
                        if (var3 != 875721283) {
                           var14 = false;
                           break label70;
                        }

                        this.q = var2.readInt();
                        if (this.q < 0 || this.q > 2) {
                           this.logWarn(true, S.L("Unsupported CRX version number: %d"), this.q);
                           var13 = false;
                           break label69;
                        }

                        var4 = var2.readInt();
                        var5 = var2.readInt();
                        this.RF = new byte[var4];
                        if (var2.read(this.RF) != var4) {
                           var15 = false;
                           break label68;
                        }

                        this.xK = new byte[var5];
                        if (var2.read(this.xK) == var5) {
                           break label71;
                        }

                        var15 = false;
                     } catch (Throwable var9) {
                        try {
                           var2.close();
                        } catch (Throwable var7) {
                           var9.addSuppressed(var7);
                        }

                        throw var9;
                     }

                     var2.close();
                     return var15;
                  }

                  var2.close();
                  return var15;
               }

               var2.close();
               return var13;
            }

            var2.close();
            return var14;
         }

         var1 = 16 + var4 + var5;
         var2.close();
      } catch (IOException var10) {
         Dw.catching(var10);
         return false;
      }

      try {
         SubInput var11 = new SubInput(this.getInput(), var1, this.getInput().getCurrentSize() - var1);
         CU var12 = new CU("app", var11, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
         var12.q(true);
         var12.process();
         this.addChild(var12);
         return true;
      } catch (IOException var8) {
         Dw.catching(var8);
         return false;
      }
   }

   public int q() {
      return this.q;
   }

   public byte[] RF() {
      return this.RF;
   }

   public byte[] xK() {
      return this.xK;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      Strings.ff(var1, "CRX version: %d\n", this.q);
      Strings.ff(var1, "RSA Public Key (X509 SubjectPublicKeyInfo block): %s\n", Formatter.byteArrayToHex(this.RF));
      Strings.ff(var1, "Signature of ZIP content: %s\n", Formatter.byteArrayToHexString(this.RF));
      return var1.toString();
   }
}
