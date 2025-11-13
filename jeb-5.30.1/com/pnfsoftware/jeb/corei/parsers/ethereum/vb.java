package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.LargeIntHandler;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class vb {
   private static final ILogger q = GlobalLog.getLogger(vb.class);
   private static LargeIntHandler RF = LargeIntHandler.create(256);
   private static BigInteger xK = new BigInteger("1AFEBABE00000000000000000000000000000000000000000000000000000000", 16);
   private BigInteger Dw;
   private List Uv = new ArrayList();
   private boolean oW;

   public vb() {
      this.Dw = BigInteger.ZERO;
   }

   public void q(long var1) {
      this.Dw = RF.truncate(BigInteger.valueOf(var1));
      this.Uv.clear();
      this.oW = false;
   }

   public BigInteger q() {
      return this.Dw == null ? null : RF.toUnsigned(this.Dw);
   }

   public BigInteger RF() {
      if (this.Uv.isEmpty()) {
         return null;
      } else {
         BigInteger var1 = (BigInteger)this.Uv.get(this.Uv.size() - 1);
         return var1 == null ? null : RF.toUnsigned(var1);
      }
   }

   public boolean q(vX var1) {
      try {
         if (this.oW) {
            return false;
         } else {
            int var2 = this.RF(var1);
            if (var2 < 0) {
               this.oW = true;
               return false;
            } else {
               if (var2 == 0) {
                  for (int var3 = 0; var3 < var1.nf.Dw; var3++) {
                     this.xK();
                  }

                  for (int var5 = 0; var5 < var1.nf.Uv; var5++) {
                     this.q(null);
                  }
               }

               if (var2 <= 1) {
                  this.Dw = RF.add(this.Dw, BigInteger.valueOf(var1.getSize()));
               }

               return true;
            }
         }
      } catch (Exception var4) {
         this.oW = true;
         return false;
      }
   }

   private int RF(vX var1) {
      if (var1.nf != null && var1.nf.Dw != null && var1.nf.Uv != null) {
         if (var1.nf.Dw > this.Uv.size()) {
            int var2 = var1.nf.Dw - this.Uv.size();

            while (var2-- > 0) {
               this.Uv.add(0, null);
            }
         }

         int var9 = var1.q();
         switch (var9) {
            case 0:
               return 0;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 22:
            case 23:
            case 24:
            case 27:
            case 28:
            case 29:
               BigInteger var12 = this.xK();
               BigInteger var15 = this.xK();
               BigInteger var17 = null;
               if (var12 != null && var15 != null) {
                  switch (var9) {
                     case 1:
                        var17 = RF.add(var12, var15);
                        break;
                     case 2:
                        var17 = RF.mulU(var12, var15);
                        break;
                     case 3:
                        var17 = RF.sub(var12, var15);
                        break;
                     case 4:
                        var17 = RF.divU(var12, var15);
                        break;
                     case 5:
                        var17 = RF.divS(var12, var15);
                        break;
                     case 6:
                        var17 = RF.remU(var12, var15);
                        break;
                     case 7:
                        var17 = RF.remS(var12, var15);
                        break;
                     case 8:
                     case 9:
                     case 10:
                     case 11:
                     case 12:
                     case 13:
                     case 14:
                     case 15:
                     case 21:
                     case 25:
                     case 26:
                     default:
                        throw new RuntimeException();
                     case 16:
                        var17 = RF.compareU(var12, var15) == -1 ? BigInteger.ONE : BigInteger.ZERO;
                        break;
                     case 17:
                        var17 = RF.compareU(var12, var15) == 1 ? BigInteger.ONE : BigInteger.ZERO;
                        break;
                     case 18:
                        var17 = RF.compare(var12, var15) == -1 ? BigInteger.ONE : BigInteger.ZERO;
                        break;
                     case 19:
                        var17 = RF.compare(var12, var15) == 1 ? BigInteger.ONE : BigInteger.ZERO;
                        break;
                     case 20:
                        var17 = RF.compare(var12, var15) == 0 ? BigInteger.ONE : BigInteger.ZERO;
                        break;
                     case 22:
                        var17 = RF.and(var12, var15);
                        break;
                     case 23:
                        var17 = RF.or(var12, var15);
                        break;
                     case 24:
                        var17 = RF.xor(var12, var15);
                        break;
                     case 27:
                        var17 = RF.shl(var15, var12.intValue());
                        break;
                     case 28:
                        var17 = RF.shr(var15, var12.intValue());
                        break;
                     case 29:
                        var17 = RF.sar(var15, var12.intValue());
                  }
               }

               this.q(var17);
               return 1;
            case 8:
            case 9:
            case 10:
            case 11:
               return 0;
            case 12:
            case 13:
            case 14:
            case 15:
            case 26:
            case 30:
            case 31:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 92:
            case 93:
            case 94:
            case 95:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 246:
            case 247:
            case 248:
            case 249:
            case 251:
            case 252:
            default:
               return 0;
            case 21:
            case 25:
               BigInteger var11 = this.xK();
               BigInteger var14 = null;
               if (var11 != null) {
                  switch (var9) {
                     case 21:
                        var14 = var11.signum() == 0 ? BigInteger.ONE : BigInteger.ZERO;
                        break;
                     case 25:
                        var14 = RF.not(var11);
                        break;
                     default:
                        throw new RuntimeException();
                  }
               }

               this.q(var14);
               return 1;
            case 32:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
               return 0;
            case 53:
               BigInteger var10 = null;
               BigInteger var13 = this.xK();
               if (var13 != null && var13.equals(BigInteger.ZERO)) {
                  var10 = xK;
               }

               this.q(var10);
               return 1;
            case 80:
               this.xK();
               return 1;
            case 81:
            case 82:
            case 83:
               return 0;
            case 84:
            case 85:
               return 0;
            case 86:
               this.Dw = this.xK();
               return 2;
            case 87:
               BigInteger var3 = this.xK();
               BigInteger var4 = this.xK();
               if (var4 == null) {
                  this.Dw = null;
                  return 2;
               } else {
                  if (var4.signum() != 0) {
                     this.Dw = var3;
                     return 2;
                  }

                  return 1;
               }
            case 88:
               this.Uv.add(RF.truncate(this.Dw));
               return 1;
            case 89:
            case 90:
               return 0;
            case 91:
               return 1;
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
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
               this.q(RF.truncate(var1.lm));
               return 1;
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
               int var16 = var1.xK();
               this.q((BigInteger)this.Uv.get(this.Uv.size() - var16));
               return 1;
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
               int var5 = var1.RF();
               int var6 = this.Uv.size() - 1;
               BigInteger var7 = (BigInteger)this.Uv.get(var6);
               BigInteger var8 = (BigInteger)this.Uv.get(var6 - var5);
               this.Uv.set(var6, var8);
               this.Uv.set(var6 - var5, var7);
               return 1;
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 250:
            case 253:
            case 254:
            case 255:
               return 0;
         }
      } else {
         return -1;
      }
   }

   private void q(BigInteger var1) {
      this.Uv.add(var1);
   }

   private BigInteger xK() {
      return (BigInteger)this.Uv.remove(this.Uv.size() - 1);
   }
}
