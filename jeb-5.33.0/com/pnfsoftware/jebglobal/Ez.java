package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ez {
   private static final ILogger pC = GlobalLog.getLogger(Ez.class);
   private int A;
   private int kS;
   private int wS;
   private int UT;
   private Tl E;
   private String sY = "";
   private String ys;
   private List ld;
   private List gp;
   private Map oT = new LinkedHashMap();
   private BytePipe fI = new BytePipe();

   Ez(byte[] var1, aI var2) throws IOException, Ae {
      String var3 = Strings.decodeASCII(var1);
      zI.pC("Command is unknown (this is a stop-reply packet)", var3);
      if (zI.A(var3)) {
         throw new Ae("This GDB client does not implement non-stop mode");
      } else {
         this.E = var2.Sc();
         char var4 = var3.charAt(0);
         this.A = var4 & 255;
         var3 = var3.substring(1);
         if (var4 == 'S' || var4 == 'T') {
            TreeMap var20 = new TreeMap();
            this.kS = zI.ys(var3.substring(0, 2));

            for (String var9 : var3.substring(2).split(";")) {
               String[] var10 = var9.split(":");
               if (var10.length != 2) {
                  pC.debug("Invalid key-pair: %s", var9);
               } else {
                  String var11 = var10[0];
                  switch (var11) {
                     case "thread":
                        int[] var13 = zI.gp(var10[1]);
                        this.wS = var13[0];
                        this.UT = var13[1];
                        break;
                     case "core":
                        this.wS = zI.ys(var10[1]);
                        break;
                     case "watch":
                     case "rwatch":
                     case "awatch":
                     case "library":
                     case "replaylog":
                     case "swbreak":
                     case "hwbreak":
                     case "fork":
                     case "vfork":
                     case "vforkdone":
                        this.sY = var10[0];
                        break;
                     case "name":
                        this.ys = var10[1];
                        break;
                     case "hexname":
                        this.ys = zI.pC(var10[1], false);
                        break;
                     case "threads":
                        this.ld = new ArrayList();

                        for (String var22 : zI.E(var10[1])) {
                           int var16 = zI.ys(var22);
                           this.ld.add(var16);
                        }
                        break;
                     case "jstopinfo":
                        this.gp = new cR(var2).kS(zI.pC(var10[1], false));
                        break;
                     default:
                        try {
                           int var14 = Integer.parseInt(var10[0], 16);
                           byte[] var15 = Formatter.hexStringToByteArray(var10[1]);
                           if (var15 != null) {
                              var20.put(var14, var15);
                           }
                        } catch (NumberFormatException var17) {
                           this.oT.put(var10[0], var10[1]);
                        }
                  }
               }
            }

            if (this.E != null) {
               this.E.pC(var20);
            }
         } else if (var4 != 'w' && var4 != 'W' && var4 != 'X') {
            if (var4 != 'O') {
               throw new Ae("Unsupported stop-reply payload code: " + var4);
            }

            byte[] var19 = Formatter.hexStringToByteArray(var3);
            this.fI.append(var19);
            Object[] var10000 = new Object[]{Formatter.escapeBytes(var19)};
         } else {
            int var5 = var3.indexOf(59);
            if (var5 < 0) {
               this.kS = zI.ys(var3);
            } else {
               this.kS = zI.ys(var3.substring(0, var5));
            }

            if (var4 != 'w') {
               if (var4 == 'W') {
                  this.sY = "exit";
               } else if (var4 == 'X') {
                  this.sY = "termination";
               }
            }
         }
      }
   }

   public int pC() {
      return this.A;
   }

   public byte[] A() {
      return this.fI.getAll();
   }

   public int kS() {
      return this.kS;
   }

   public int wS() {
      return this.wS;
   }

   public int UT() {
      return this.UT;
   }

   public List E() {
      return this.gp;
   }

   public Tl sY() {
      return this.E;
   }

   public boolean ys() {
      return Strings.isContainedIn(this.sY, "termination", "exit");
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.ys != null) {
         var1.append(this.ys + " / ");
      }

      Strings.ff(var1, "%s(%d) / pid=%d / tid=%d / reason=%s", yv.pC(this.kS), this.kS, this.wS, this.UT, this.sY);
      if (this.E != null && this.E.wS() > 0) {
         var1.append(" / ");

         for (Couple var3 : this.E.UT()) {
            int var4 = ((RegisterDescriptionEntry)var3.getFirst()).getNumber();
            byte[] var5 = (byte[])var3.getSecond();
            if (var5 != null) {
               Strings.ff(var1, "%d:%s ", var4, RegisterUtil.byteArrayToHex(this.E.A(), var5));
            }
         }
      }

      return var1.toString();
   }
}
