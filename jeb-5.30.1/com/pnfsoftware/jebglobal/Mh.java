package com.pnfsoftware.jebglobal;

class Mh {
   private static final Ef[] q = new Ef[]{Y.xW, Y.CE, Y.wF};
   private static final Je[] RF = new Je[]{
      new Qg(0, "VADDL", Zo.RF, q),
      new Qg(1, "VADDL", Zo.Uv, q),
      new Qg(2, "VADDW", Zo.RF, Y.xW, Y.KT, Y.wF),
      new Qg(3, "VADDW", Zo.Uv, Y.xW, Y.KT, Y.wF),
      new Qg(4, "VSUBL", Zo.RF, q),
      new Qg(5, "VSUBL", Zo.Uv, q),
      new Qg(6, "VSUBW", Zo.RF, Y.xW, Y.KT, Y.wF),
      new Qg(7, "VSUBW", Zo.Uv, Y.xW, Y.KT, Y.wF),
      new Qg(8, "VADDHN", Zo.lm, Y.sH, Y.KT, Y.Gf),
      new Qg(9, "VRADDHN", Zo.lm, Y.sH, Y.KT, Y.Gf),
      new Qg(10, "VABAL", Zo.RF, q),
      new Qg(11, "VABAL", Zo.Uv, q),
      new Qg(12, "VSUBHN", Zo.lm, Y.sH, Y.KT, Y.Gf),
      new Qg(13, "VRSUBHN", Zo.lm, Y.sH, Y.KT, Y.Gf),
      new Qg(14, "VABDL", Zo.RF, q),
      new Qg(15, "VABDL", Zo.Uv, q),
      new Qg(0, "VMLAL", Zo.RF, q),
      new Qg(1, "VMLAL", Zo.Uv, q),
      new Qg(2, "VQDMLAL", Zo.Dw, q),
      Qg.xK,
      new Qg(4, "VMLSL", Zo.RF, q),
      new Qg(5, "VMLSL", Zo.Uv, q),
      new Qg(6, "VQDMLSL", Zo.Dw, q),
      Qg.xK,
      new Qg(8, "VMULL", Zo.RF, q),
      new Qg(9, "VMULL", Zo.Uv, q),
      new Qg(10, "VQDMULL", Zo.Dw, q),
      Qg.xK,
      new Qg(12, "VMULL", new Mh.eo('P'), q),
      Qg.xK,
      Qg.xK,
      Qg.xK
   };

   public static Je q(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = Zo.q(var0, var1);
      return RF[var2 << 1 | var3];
   }

   private static class eo implements de.eo {
      private Character sH;

      public eo(Character var1) {
         this.sH = var1;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws eK {
         StringBuilder var2 = new StringBuilder();
         if (this.sH != null) {
            var2.append(this.sH);
         }

         int var3 = Zo.q(var1);
         switch (var3) {
            case 0:
               var2.append('8');
               break;
            case 2:
               var2.append("64");
               break;
            default:
               return null;
         }

         return var2;
      }
   }
}
