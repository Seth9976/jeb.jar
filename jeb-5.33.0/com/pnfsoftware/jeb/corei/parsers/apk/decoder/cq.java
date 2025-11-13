package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jebglobal.Ij;
import com.pnfsoftware.jebglobal.g;
import com.pnfsoftware.jebglobal.sh;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

@Ser
public class cq extends AbstractBinaryUnit {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);
   @SerId(1)
   private short[] A;

   public cq(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "arsc_meta", var1, var3, var4, var5);
   }

   @Override
   protected boolean processInternal() {
      try {
         try (DataInputStream var1 = new DataInputStream(this.getInput().getStream())) {
            int var2 = var1.readInt();
            this.A = new short[var2];

            for (int var3 = 0; var3 < var2; var3++) {
               this.A[var3] = var1.readShort();
            }

            int var21 = 0;

            for (short var7 : this.A) {
               String var8 = Strings.ff("%c%c", var7 >> 8 & 0xFF, var7 & 255);
               String var9 = Strings.ff("resources-%s.arsc", var8);
               Object[] var10000 = new Object[]{var8, var7};

               try (InputStream var10 = this.getInput().getStream()) {
                  byte[] var11 = pC(var10, var7, var21);
                  BytesInput var12 = new BytesInput(var11);
                  String var13 = "arsc";
                  IUnit var14 = this.getUnitProcessor().process(var9, var12, this, var13, true);
                  if (var14 != null) {
                     this.addChild(var14);
                  }
               } catch (Exception var18) {
                  this.logException(var18);
               }

               var21++;
            }
         }

         return true;
      } catch (IOException var20) {
         pC.catching(var20);
         return false;
      }
   }

   private static byte[] pC(InputStream var0, short var1, int var2) throws IOException {
      DataInputStream var3 = new DataInputStream(var0);
      int var4 = var3.readInt();
      var3.skip(var4 * 2);
      g var5 = null;

      for (int var6 = 0; var6 < var4; var6++) {
         if (var6 == var2) {
            var5 = new g(var3);
            Preconditions.checkArgument(var1 == EndianUtil.bigEndianBytesToShort(var5.kS));
         } else {
            g.pC(var3);
         }
      }

      Ij var7 = Ij.pC(new LittleEndianDataInputStream(var3), null, var5);
      ((sh)var7).A(false);
      return var7.pC(false);
   }

   @Override
   public String getDescription() {
      String var1 = super.getDescription();
      return var1 + Strings.ff(S.L("\nLocalized for %d locales"), this.A.length);
   }
}
