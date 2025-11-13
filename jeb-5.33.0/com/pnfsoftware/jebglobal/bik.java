package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElementFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;

@Ser
public class bik {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.vi pC;
   @SerId(2)
   private cdk A;
   @SerId(3)
   private biy kS;
   @SerId(4)
   private bid wS;
   @SerId(5)
   private bib UT;
   @SerId(6)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws E;
   @SerId(7)
   private IJavaElementFactory sY;

   @SerCustomInitPostGraph
   private void pC() {
      if (this.E == null) {
         this.E = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)DecompilerHelper.getDecompiler(this.pC, false);
      }

      if (this.sY == null && this.E != null) {
         this.sY = this.E.A();
      }
   }

   public bik(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1, cdk var2, biy var3, bid var4, bib var5, IJavaElementFactory var6) {
      this.E = var1;
      this.pC = var1.ld();
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
      this.sY = var6;
   }

   public IJavaExpression pC(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            return this.kS.createByte(var1.getByte());
         case 1:
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         default:
            return this.kS.createString(Strings.ff("<_unknown_vt=%d>", var1.getType()));
         case 2:
            return this.kS.createShort(var1.getShort());
         case 3:
            return this.kS.createChar(var1.getChar());
         case 4:
            return this.kS.createInt(var1.getInt());
         case 6:
            return this.kS.createLong(var1.getLong());
         case 16:
            return this.kS.createFloat(var1.getFloat());
         case 17:
            return this.kS.createDouble(var1.getDouble());
         case 23:
            return this.kS.createString(this.pC.pC(var1.getStringIndex(), ""));
         case 24:
            String var7 = this.pC.pC(var1.getTypeIndex(), false);
            return this.sY.createStaticField(this.A.pC(var7), (String)null);
         case 25:
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("Field Value not generated"));
            return this.kS.createString("");
         case 26:
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("Method Value not generated"));
            return this.kS.createString("");
         case 27:
            bft var6 = this.pC.E(var1.getEnumIndex());
            String var8 = var6.getClassTypeSignature(false);
            return this.sY.createStaticField(this.A.pC(var8), var6.getSignature(false));
         case 28:
            ArrayList var2 = new ArrayList();

            for (IDexValue var4 : var1.getArray()) {
               IJavaExpression var5 = this.pC(var4);
               var2.add(var5);
            }

            return new bjw(null, false, var2);
         case 29:
            return this.E.pC(var1.getAnnotation());
         case 30:
            return this.kS.pC();
         case 31:
            return this.kS.createBoolean(var1.getBoolean());
      }
   }
}
