package com.pnfsoftware.jebglobal;

class Zu {
   private static final Hu[] pC = new Hu[]{BS.ah, BS.Ek, BS.hK};
   private static final tz[] A = new tz[]{
      new UC(0, "VADDL", Uf.A, pC),
      new UC(1, "VADDL", Uf.UT, pC),
      new UC(2, "VADDW", Uf.A, BS.ah, BS.eP, BS.hK),
      new UC(3, "VADDW", Uf.UT, BS.ah, BS.eP, BS.hK),
      new UC(4, "VSUBL", Uf.A, pC),
      new UC(5, "VSUBL", Uf.UT, pC),
      new UC(6, "VSUBW", Uf.A, BS.ah, BS.eP, BS.hK),
      new UC(7, "VSUBW", Uf.UT, BS.ah, BS.eP, BS.hK),
      new UC(8, "VADDHN", Uf.oT, BS.z, BS.eP, BS.UO),
      new UC(9, "VRADDHN", Uf.oT, BS.z, BS.eP, BS.UO),
      new UC(10, "VABAL", Uf.A, pC),
      new UC(11, "VABAL", Uf.UT, pC),
      new UC(12, "VSUBHN", Uf.oT, BS.z, BS.eP, BS.UO),
      new UC(13, "VRSUBHN", Uf.oT, BS.z, BS.eP, BS.UO),
      new UC(14, "VABDL", Uf.A, pC),
      new UC(15, "VABDL", Uf.UT, pC),
      new UC(0, "VMLAL", Uf.A, pC),
      new UC(1, "VMLAL", Uf.UT, pC),
      new UC(2, "VQDMLAL", Uf.wS, pC),
      UC.pC,
      new UC(4, "VMLSL", Uf.A, pC),
      new UC(5, "VMLSL", Uf.UT, pC),
      new UC(6, "VQDMLSL", Uf.wS, pC),
      UC.pC,
      new UC(8, "VMULL", Uf.A, pC),
      new UC(9, "VMULL", Uf.UT, pC),
      new UC(10, "VQDMULL", Uf.wS, pC),
      UC.pC,
      new UC(12, "VMULL", new Zu.Av('P'), pC),
      UC.pC,
      UC.pC,
      UC.pC
   };

   public static tz pC(byte[] var0, int var1) {
      int var2 = var0[2] & 15;
      int var3 = Uf.pC(var0, var1);
      return A[var2 << 1 | var3];
   }

   private static class Av implements YE {
      private Character Er;

      public Av(Character var1) {
         this.Er = var1;
      }

      @Override
      public CharSequence getDataType(byte[] var1) throws oJ {
         StringBuilder var2 = new StringBuilder();
         if (this.Er != null) {
            var2.append(this.Er);
         }

         int var3 = Uf.pC(var1);
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
