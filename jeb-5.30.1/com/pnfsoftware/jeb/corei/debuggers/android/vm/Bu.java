package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;

public class Bu extends LR {
   private static final ILogger q = GlobalLog.getLogger(Bu.class);
   private boolean RF = false;
   private ch xK;
   private String Dw;
   private CI Uv;
   private ITypedValue oW;

   public Bu(String var1, int var2, CI var3, LR.eo var4, ch var5, String var6) {
      super(var1, null, var2, var3, var4);
      this.xK = var5;
      this.Dw = var6;
      this.Uv = var3;
   }

   @Override
   public ITypedValue getTypedValue() {
      this.Dw();
      return this.oW;
   }

   @Override
   public boolean setTypedValue(ITypedValue var1) {
      this.Dw();
      boolean var2 = super.setTypedValue(var1);
      if (var2) {
         this.oW = super.getTypedValue();
      }

      return var2;
   }

   private void Dw() {
      if (!this.RF) {
         try {
            this.oW = this.Uv.q(this.xK, this.Dw);
            this.RF = true;
         } catch (zy | IOException var2) {
            q.catching(var2);
         }
      }
   }

   @Override
   public ch q() {
      return this.xK;
   }

   public static LR q(String var0, int var1, CI var2, LR.eo var3, ch var4, String var5) throws zy, IOException {
      switch (var4.q) {
         case 66:
         case 67:
         case 68:
         case 70:
         case 73:
         case 74:
         case 83:
         case 86:
         case 90:
            return new LR(var0, var2.q(var4, var5), var1, var2, var3);
         case 69:
         case 71:
         case 72:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 84:
         case 85:
         case 87:
         case 88:
         case 89:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         default:
            return new Bu(var0, var1, var2, var3, var4, var5);
      }
   }
}
