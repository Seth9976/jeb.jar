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
public class bmh {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   @SerId(2)
   private cis RF;
   @SerId(3)
   private bmv xK;
   @SerId(4)
   private bma Dw;
   @SerId(5)
   private bly Uv;
   @SerId(6)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej oW;
   @SerId(7)
   private IJavaElementFactory gO;

   @SerCustomInitPostGraph
   private void q() {
      if (this.oW == null) {
         this.oW = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)DecompilerHelper.getDecompiler(this.q, false);
      }

      if (this.gO == null && this.oW != null) {
         this.gO = this.oW.RF();
      }
   }

   public bmh(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1, cis var2, bmv var3, bma var4, bly var5, IJavaElementFactory var6) {
      this.oW = var1;
      this.q = var1.TX();
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
      this.gO = var6;
   }

   public IJavaExpression q(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            return this.xK.createByte(var1.getByte());
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
            return this.xK.createString(Strings.ff("<_unknown_vt=%d>", var1.getType()));
         case 2:
            return this.xK.createShort(var1.getShort());
         case 3:
            return this.xK.createChar(var1.getChar());
         case 4:
            return this.xK.createInt(var1.getInt());
         case 6:
            return this.xK.createLong(var1.getLong());
         case 16:
            return this.xK.createFloat(var1.getFloat());
         case 17:
            return this.xK.createDouble(var1.getDouble());
         case 23:
            return this.xK.createString(this.q.q(var1.getStringIndex(), ""));
         case 24:
            String var7 = this.q.q(var1.getTypeIndex(), false);
            return this.gO.createStaticField(this.RF.q(var7), (String)null);
         case 25:
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("Field Value not generated"));
            return this.xK.createString("");
         case 26:
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("Method Value not generated"));
            return this.xK.createString("");
         case 27:
            bjo var6 = this.q.oW(var1.getEnumIndex());
            String var8 = var6.getClassTypeSignature(false);
            return this.gO.createStaticField(this.RF.q(var8), var6.getSignature(false));
         case 28:
            ArrayList var2 = new ArrayList();

            for (IDexValue var4 : var1.getArray()) {
               IJavaExpression var5 = this.q(var4);
               var2.add(var5);
            }

            return new bnt(null, false, var2);
         case 29:
            return this.oW.q(var1.getAnnotation());
         case 30:
            return this.xK.q();
         case 31:
            return this.xK.createBoolean(var1.getBoolean());
      }
   }
}
