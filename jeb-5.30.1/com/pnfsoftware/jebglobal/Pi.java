package com.pnfsoftware.jebglobal;

public class Pi {
   public static final byte q = 91;
   public static final byte RF = 66;
   public static final byte xK = 67;
   public static final byte Dw = 76;
   public static final byte Uv = 70;
   public static final byte oW = 68;
   public static final byte gO = 73;
   public static final byte nf = 74;
   public static final byte gP = 83;
   public static final byte za = 86;
   public static final byte lm = 90;
   public static final byte zz = 115;
   public static final byte JY = 116;
   public static final byte HF = 103;
   public static final byte LK = 108;
   public static final byte io = 99;

   public static boolean q(byte var0) {
      switch (var0) {
         case 66:
         case 67:
         case 68:
         case 70:
         case 73:
         case 74:
         case 83:
         case 90:
            return true;
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
         case 86:
         case 87:
         case 88:
         case 89:
         default:
            return false;
      }
   }

   public static boolean RF(byte var0) {
      switch (var0) {
         case 76:
         case 91:
         case 99:
         case 103:
         case 108:
         case 115:
         case 116:
            return true;
         default:
            return false;
      }
   }

   public static boolean xK(byte var0) {
      return var0 == 115;
   }

   public static boolean Dw(byte var0) {
      return var0 == 91;
   }

   public static boolean Uv(byte var0) {
      switch (var0) {
         case 91:
         case 99:
         case 103:
         case 108:
         case 115:
         case 116:
            return true;
         default:
            return false;
      }
   }

   public static void oW(byte var0) {
      switch (var0) {
         case 66:
         case 67:
         case 68:
         case 70:
         case 73:
         case 74:
         case 83:
         case 90:
            return;
         case 69:
         case 71:
         case 72:
         case 75:
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
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 100:
         case 101:
         case 102:
         case 104:
         case 105:
         case 106:
         case 107:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         default:
            throw new si("Illegal Tag: " + var0);
         case 76:
         case 86:
         case 91:
         case 99:
         case 103:
         case 108:
         case 115:
         case 116:
      }
   }

   public static enum eo {
      q(91),
      RF(66),
      xK(67),
      Dw(76),
      Uv(70),
      oW(68),
      gO(73),
      nf(74),
      gP(83),
      za(86),
      lm(90),
      zz(115),
      JY(116),
      HF(103),
      LK(108),
      io(99);

      private final int qa;

      private eo(int var3) {
         this.qa = var3;
      }

      public byte q() {
         return (byte)this.qa;
      }
   }
}
