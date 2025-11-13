package com.pnfsoftware.jeb.corei.parsers.apk.decoder;

import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.TextBuilder;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import com.pnfsoftware.jebglobal.Aj;
import com.pnfsoftware.jebglobal.HK;
import com.pnfsoftware.jebglobal.Kd;
import com.pnfsoftware.jebglobal.L;
import com.pnfsoftware.jebglobal.oI;
import com.pnfsoftware.jebglobal.ta;
import com.pnfsoftware.jebglobal.tt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class vb {
   private static final ILogger q = GlobalLog.getLogger(vb.class);
   private boolean RF;
   private Kd xK;
   private Map Dw;

   public vb(Nz var1, boolean var2) {
      this.RF = var2;
      this.xK = var1.q;
      this.Dw = var1.oW;
   }

   public String q() {
      TextBuilder var1 = new TextBuilder(2);
      ArrayList var2 = new ArrayList(this.xK.RF());
      Collections.sort(var2, new oL(this));

      for (tt var4 : var2) {
         var1.appendLine("Package name=%s id=%x", var4.gO, var4.oW);
         var1.indent();
         if (!this.RF) {
            for (L var6 : var4.HF) {
               var1.appendLine(true);
               var1.appendLine("library (%d)", var6.q().size());
               var1.indent();

               for (Aj var8 : var6.q()) {
                  var1.appendLine("id=%x -> %s", var8.q(), var8.RF());
               }

               var1.unindent();
            }
         }

         HashMap var18 = new HashMap();
         TreeMap var19 = new TreeMap();

         for (int var22 : this.Dw.keySet()) {
            if (var22 >>> 24 == var4.oW) {
               int var9 = var22 >>> 16;
               int var10 = var9 & 0xFF;
               String var11 = var4.lm.q(var10 - 1);
               var19.put(var11, var10);
               Maps.putMulti(var18, var10, (tl)this.Dw.get(var22));
            }
         }

         ArrayList var21 = new ArrayList(var19.keySet());
         Collections.sort(var21, new Vj(this));

         for (String var24 : var21) {
            int var25 = (Integer)var19.get(var24);
            List var26 = (List)var18.get(var25);
            var1.appendLine(true);
            var1.appendLine("type %s id=%x entryCount=%d", var24, var25, var26.size());
            var1.indent();

            for (tl var13 : var26) {
               var1.appendLine("spec resource 0x%08x %s%s", var13.q, var13.RF, this.q(var13.Uv, var13.oW.values()));
               var1.indent();

               for (Entry var15 : var13.oW.entrySet()) {
                  String var16 = (String)var15.getKey();
                  var1.append("(%s) ", var16);
                  ta var17 = (ta)var15.getValue();
                  this.q(var13.q, var17, var24, var1);
                  var1.appendLine();
               }

               var1.unindent();
            }

            var1.unindent();
         }

         var1.unindent();
      }

      return var1.toString();
   }

   private void q(int var1, ta var2, String var3, TextBuilder var4) {
      if (var2.q()) {
         this.q(var2.gO, var3, var4);
      } else {
         var4.append("(%s)", this.q(var3));
         boolean var5 = var2.gP.isEmpty();
         boolean var6 = var3.equals("array");
         boolean var7 = var3.equals("style");
         boolean var8 = var3.equals("plurals");
         ArrayList var9 = new ArrayList(var2.gP);
         if (var8 && var2.gP.size() >= 2) {
            Collections.sort(var9, new Bu(this));
         }

         if (!var5 || var6 || var7 || var8) {
            if (var6) {
               var4.append((CharSequence)" [");
            } else if (var7) {
               if (var2.nf != 0) {
                  var4.append((CharSequence)" parent=");
                  String var10 = this.RF(var2.nf);
                  if (var10 != null) {
                     var4.append("%s ", var10);
                  }

                  var4.append("0x%08x", var2.nf);
               }

               if (!var5) {
                  var4.appendLine();
                  var4.indent();
               }
            } else {
               var4.append((CharSequence)" ");
            }

            int var15 = 0;
            boolean var11 = false;

            for (oI var13 : var9) {
               if (var15 >= 1) {
                  if (var6) {
                     var4.append((CharSequence)", ");
                  }

                  if (var7) {
                     var4.appendLine();
                  }

                  if (var8) {
                     var4.append((CharSequence)" ");
                  }

                  if (var11) {
                     var4.appendLine();
                  }
               }

               if (var7) {
                  String var14 = this.q(var13.q, false, true);
                  var4.append("%s(0x%08x)=", Strings.safe(var14), var13.q);
               }

               boolean var16 = this.q(var13, var3, var4, var11, var8);
               if (var15 == 0 && var16) {
                  var11 = true;
                  var4.indent();
               }

               var15++;
            }

            if (!var5 && (var11 || var7)) {
               var4.unindent();
            }

            if (var6) {
               var4.append((CharSequence)"]");
            }
         }
      }
   }

   private void q(HK var1, String var2, TextBuilder var3) {
      String var4;
      if (var2.equals("id")) {
         var4 = "(id)";
         if (!this.RF) {
            var4 = var4 + Strings.ff(" 0x%08x", var1.xK);
         }
      } else {
         switch (var1.RF) {
            case 0:
               if (var1.xK == 0) {
                  var4 = "(undefined) ?";
               } else if (var1.xK == 1) {
                  var4 = "(empty)";
               } else {
                  var4 = "(illegal) " + var1.xK;
               }
               break;
            case 1:
               if (var1.xK == 0) {
                  var4 = "(reference) @null";
               } else {
                  String var11 = this.RF(var1.xK);
                  if (var11 == null) {
                     var4 = Strings.ff("(reference) @0x%08x", var1.xK);
                  } else {
                     var4 = Strings.ff("(reference) @%s 0x%08x", var11, var1.xK);
                  }
               }
               break;
            case 2:
               String var10 = this.RF(var1.xK);
               var4 = Strings.ff("(attr-reference) ?%s0x%08x", var10 == null ? "" : var10 + " ", var1.xK);
               break;
            case 3:
               String var5 = this.xK.q().q(var1.xK);
               String var6 = this.xK.q().RF(var1.xK);
               boolean var7 = !Strings.isBlank(var6);
               String var8 = "string";
               String var9 = "\"";
               if (var7) {
                  var8 = "styled string";
               } else if (this.RF(var5)) {
                  var8 = "file";
                  var9 = "";
               }

               var4 = Strings.ff("(%s) %s%s%s", var8, var9, var5, var9);
               if (var7) {
                  var4 = var4 + " " + var6;
               }
               break;
            case 4:
               if (this.RF) {
                  var4 = Strings.ff("(unknown 0x%x) 0x%x", var1.RF, var1.xK);
               } else {
                  var4 = "(float) " + Float.toString(Float.intBitsToFloat(var1.xK));
               }
               break;
            case 5:
               if (this.RF) {
                  var4 = Strings.ff("(unknown 0x%x) 0x%x", var1.RF, var1.xK);
               } else {
                  var4 = "(dimension) " + Float.toString(kY.q(var1.xK)) + kY.q[var1.xK >> 0 & 15];
               }
               break;
            case 6:
               if (this.RF) {
                  var4 = Strings.ff("(unknown 0x%x) 0x%x", var1.RF, var1.xK);
               } else {
                  var4 = "(fraction) " + Float.toString(kY.q(var1.xK) * 100.0F) + kY.RF[var1.xK >> 0 & 15];
               }
               break;
            case 7:
               var4 = "(dynref) ";
               if (!this.RF) {
                  var4 = var4 + Strings.ff("0x%08x", var1.xK);
               }
               break;
            case 8:
               var4 = "(dynattr) ";
               if (!this.RF) {
                  var4 = var4 + Strings.ff("0x%08x", var1.xK);
               }
               break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            default:
               throw new RuntimeException(Strings.ff("Don't know how to format value type: %d (0x%X)", var1.RF, var1.RF));
            case 16:
               var4 = Strings.ff("(integer) %d", var1.xK);
               break;
            case 17:
               var4 = Strings.ff("(integer) 0x%x", var1.xK);
               break;
            case 18:
               var4 = Strings.ff("(boolean) %s", var1.xK != 0 ? "true" : "false");
               break;
            case 28:
               var4 = Strings.ff("(color) #%x", var1.xK);
               break;
            case 29:
               var4 = Strings.ff("(color) #%x", var1.xK);
               break;
            case 30:
               var4 = Strings.ff("(color) #%x", var1.xK);
               break;
            case 31:
               var4 = Strings.ff("(color) #%x", var1.xK);
         }
      }

      var3.append((CharSequence)var4);
   }

   private boolean q(oI var1, String var2, TextBuilder var3, boolean var4, boolean var5) {
      switch (var1.q) {
         case 16777216:
            var4 = false;
            int var13 = var1.RF.xK & 65535;
            ArrayList var7 = new ArrayList();
            if (var13 == 65535) {
               var7.add("any");
            } else {
               if ((var13 & 1) != 0) {
                  var7.add("reference");
               }

               if ((var13 & 2) != 0) {
                  var7.add("string");
               }

               if ((var13 & 4) != 0) {
                  var7.add("integer");
               }

               if ((var13 & 8) != 0) {
                  var7.add("boolean");
               }

               if ((var13 & 16) != 0) {
                  var7.add("color");
               }

               if ((var13 & 32) != 0) {
                  var7.add("float");
               }

               if ((var13 & 64) != 0) {
                  var7.add("dimension");
               }

               if ((var13 & 128) != 0) {
                  var7.add("fraction");
               }
            }

            int var8 = var1.RF.xK & -65536;
            if ((var8 & 65536) != 0) {
               var7.add("enum");
               var4 = true;
            }

            if ((var8 & 131072) != 0) {
               var7.add("flags");
               var4 = true;
            }

            var3.append((CharSequence)"type=");
            int var9 = 0;

            for (String var11 : var7) {
               if (var9 >= 1) {
                  var3.append((CharSequence)"|");
               }

               var3.append((CharSequence)var11);
               var9++;
            }

            return var4;
         case 16777220:
         case 16777221:
         case 16777222:
         case 16777223:
         case 16777224:
         case 16777225:
            if (var5) {
               var3.append("%s=", oI.RF(var1.q));
               this.q(var1.RF, var2, var3);
               return false;
            }
         case 16777217:
         case 16777218:
         case 16777219:
         default:
            if (var4) {
               String var6 = this.xK(var1.q);
               var3.append("%s (0x%08x) = %s", var6, var1.q, Integers.toUnsignedString(var1.RF.xK));
               return true;
            } else {
               this.q(var1.RF, var2, var3);
               return false;
            }
      }
   }

   private String q(int var1, Collection var2) {
      if ((var1 & 1073741824) != 0) {
         return " PUBLIC";
      } else {
         for (ta var4 : var2) {
            if (!var4.xK()) {
               return "";
            }
         }

         return " PUBLIC";
      }
   }

   String q(int var1) {
      String var2 = this.RF(var1);
      return var2 != null ? var2 : Strings.ff("ResourceId_0x%08x", var1);
   }

   String RF(int var1) {
      return this.q(var1, false, false);
   }

   String q(int var1, boolean var2, boolean var3) {
      tl var4 = (tl)this.Dw.get(var1);
      if (var4 == null) {
         return null;
      } else {
         String var5 = var4.RF;
         if (var5 == null) {
            throw new RuntimeException(Strings.ff("Resource 0x%08x has a null name", var1));
         } else {
            if (var2) {
               var5 = var5.substring(var5.indexOf(58) + 1);
            }

            if (var3) {
               if (var2) {
                  var5 = var5.substring(var5.indexOf(47) + 1);
               } else {
                  var5 = var5.substring(0, var5.indexOf(58)) + ":" + var5.substring(var5.indexOf(47) + 1);
               }
            }

            return var5;
         }
      }
   }

   String xK(int var1) {
      String var2 = this.RF(var1);
      if (var2 == null) {
         return null;
      } else {
         int var3 = var2.indexOf(47);
         if (var3 >= 0) {
            var2 = var2.substring(var3 + 1);
         }

         return var2;
      }
   }

   private String q(String var1) {
      if (var1.equals("plurals")) {
         return "plural";
      } else {
         return var1.equals("^attr-private") ? "attr" : var1;
      }
   }

   private boolean RF(String var1) {
      return var1.startsWith("res/");
   }
}
