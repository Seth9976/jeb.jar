package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JF extends jA {
   public static final int oW = -1;
   public static final int gO = 1;
   public static final int nf = 2;
   int gP;
   int za;
   public Sp lm;
   boolean zz;
   boolean JY;
   int[] HF;
   public List LK = new ArrayList();

   JF(jA var1, uL var2) throws IOException {
      super(var1, 513);
      this.gP = var2.readUnsignedByte();
      if (this.gP == 0) {
         throw new IOException("Zero id in resource specification");
      } else {
         this.za = var2.readUnsignedByte();
         var2.readShort();
         int var3 = var2.readInt();
         int var4 = var2.readInt();
         int var5 = var1.Dw - var1.nf(var2);
         this.lm = Sp.q(var2, var5);
         this.xK(var2);
         this.zz = (this.za & 1) != 0;
         this.JY = (this.za & 2) != 0;
         if (this.JY) {
            byte[] var6 = new byte[var3 * 2];
            var2.readFully(var6);
            this.HF = new int[var3];
            byte var7 = 0;

            for (int var8 = 0; var8 < var3; var8++) {
               short var9 = EndianUtil.littleEndianBytesToShort(var6, var7);
               if (var9 == -1) {
                  this.HF[var8] = -1;
               } else {
                  this.HF[var8] = (var9 & '\uffff') * 4;
               }

               var7 += 2;
            }
         } else {
            this.HF = zR.q(var2, var3);
         }

         this.q(var2, var4);
         byte[] var16 = new byte[this.gP(var2)];
         var2.readFully(var16);
         aq var17 = new aq(var16, 0, var16.length);
         if (this.zz) {
            for (int var18 = 0; var18 < this.HF.length; var18++) {
               int var20 = this.HF[var18] & 65535;
               int var10 = (this.HF[var18] >>> 16) * 4;
               var17.RF(var10);

               ta var11;
               try {
                  var11 = new ta(var17);
               } catch (IOException var15) {
                  var2.q(S.L("Bad entry, aborting: %s"), var15.getMessage());
                  break;
               }

               var11.za = var20;
               this.LK.add(var11);
            }
         } else {
            int var19 = var17.q();
            int var21 = 0;

            for (int var22 = 0; var22 < this.HF.length; var22++) {
               ta var23 = null;
               int var12 = this.HF[var22];
               if (var12 != -1) {
                  var17.RF(var12);
                  if (var12 != var19 && var17.RF() < 8) {
                     var2.q(
                        S.L("Reading of ResTable_entry @ offset 0x%X will fail as there is not enough data -> forcing a read at a safer expected offset 0x%X"),
                        var12,
                        var19
                     );
                     var17.RF(var19);
                  }

                  try {
                     var23 = new ta(var17);
                  } catch (IOException var14) {
                     var2.q(S.L("Bad entry, aborting: %s"), var14.getMessage());
                     break;
                  }

                  if (var23.oW()) {
                     break;
                  }

                  var23.za = var21;
               }

               this.LK.add(var23);
               var21++;
               var19 = var17.q();
            }
         }
      }
   }

   @Override
   public void q(TextBuilder var1) {
      var1.appendLine("Type: id=%Xh, flags=%Xh", this.gP, this.za);
      var1.indent();

      for (ta var3 : this.LK) {
         if (var3 == null) {
            var1.appendLine("<No-Entry>");
         } else {
            var3.q(var1);
         }
      }

      var1.unindent();
   }

   public int q() {
      return this.gP;
   }

   public int RF() {
      return this.za;
   }

   public String xK() {
      StringBuilder var1 = new StringBuilder();
      if ((this.za & 1) != 0) {
         var1.append("sparse");
      }

      return var1.toString();
   }
}
