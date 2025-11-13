package com.pnfsoftware.jeb.corei.parsers.crx;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.corei.parsers.zip.Sv;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;

@Ser
public class Av extends AbstractBinaryUnit {
   private static final ILogger wS = GlobalLog.getLogger(Av.class);
   @SerId(1)
   int pC;
   @SerId(2)
   byte[] A;
   @SerId(3)
   byte[] kS;

   public Av(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
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
                  boolean var16;
                  label68: {
                     try {
                        int var3 = var2.readInt();
                        if (var3 != 875721283) {
                           var15 = false;
                           break label70;
                        }

                        this.pC = var2.readInt();
                        if (this.pC < 0 || this.pC > 2) {
                           this.logWarn(true, S.L("Unsupported CRX version number: %d"), this.pC);
                           var14 = false;
                           break label69;
                        }

                        var4 = var2.readInt();
                        var5 = var2.readInt();
                        this.A = new byte[var4];
                        if (var2.read(this.A) != var4) {
                           var16 = false;
                           break label68;
                        }

                        this.kS = new byte[var5];
                        if (var2.read(this.kS) == var5) {
                           break label71;
                        }

                        var16 = false;
                     } catch (Throwable var9) {
                        try {
                           var2.close();
                        } catch (Throwable var7) {
                           var9.addSuppressed(var7);
                        }

                        throw var9;
                     }

                     var2.close();
                     return var16;
                  }

                  var2.close();
                  return var16;
               }

               var2.close();
               return var14;
            }

            var2.close();
            return var15;
         }

         var1 = 16 + var4 + var5;
         var2.close();
      } catch (IOException var10) {
         wS.catching(var10);
         return false;
      }

      try {
         int var11 = (int)(this.getInput().getCurrentSize() - var1);
         BytesInput var12 = new BytesInput(this.getInput(), var1, var11);
         Sv var13 = new Sv("app", var12, this.getUnitProcessor(), this, this.getPropertyDefinitionManager());
         var13.pC(true);
         var13.process();
         this.addChild(var13);
         return true;
      } catch (IOException var8) {
         wS.catching(var8);
         return false;
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      Strings.ff(var1, "CRX version: %d\n", this.pC);
      Strings.ff(var1, "RSA Public Key (X509 SubjectPublicKeyInfo block): %s\n", Formatter.byteArrayToHex(this.A));
      Strings.ff(var1, "Signature of ZIP content: %s\n", Formatter.byteArrayToHexString(this.A));
      return var1.toString();
   }
}
