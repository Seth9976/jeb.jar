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

public class Yl {
   private static final ILogger q = GlobalLog.getLogger(Yl.class);
   private int RF;
   private int xK;
   private int Dw;
   private int Uv;
   private kW oW;
   private String gO = "";
   private String nf;
   private List gP;
   private List za;
   private Map lm = new LinkedHashMap();
   private BytePipe zz = new BytePipe();

   Yl(byte[] var1, oH var2) throws IOException, WI {
      String var3 = Strings.decodeASCII(var1);
      MO.q("Command is unknown (this is a stop-reply packet)", var3);
      if (MO.RF(var3)) {
         throw new WI("This GDB client does not implement non-stop mode");
      } else {
         this.oW = var2.Me();
         char var4 = var3.charAt(0);
         this.RF = var4 & 255;
         var3 = var3.substring(1);
         if (var4 == 'S' || var4 == 'T') {
            TreeMap var20 = new TreeMap();
            this.xK = MO.nf(var3.substring(0, 2));

            for (String var9 : var3.substring(2).split(";")) {
               String[] var10 = var9.split(":");
               if (var10.length != 2) {
                  q.debug("Invalid key-pair: %s", var9);
               } else {
                  String var11 = var10[0];
                  switch (var11) {
                     case "thread":
                        int[] var13 = MO.lm(var10[1]);
                        this.Dw = var13[0];
                        this.Uv = var13[1];
                        break;
                     case "core":
                        this.Dw = MO.nf(var10[1]);
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
                        this.gO = var10[0];
                        break;
                     case "name":
                        this.nf = var10[1];
                        break;
                     case "hexname":
                        this.nf = MO.q(var10[1], false);
                        break;
                     case "threads":
                        this.gP = new ArrayList();

                        for (String var22 : MO.oW(var10[1])) {
                           int var16 = MO.nf(var22);
                           this.gP.add(var16);
                        }
                        break;
                     case "jstopinfo":
                        this.za = new bz(var2).xK(MO.q(var10[1], false));
                        break;
                     default:
                        try {
                           int var14 = Integer.parseInt(var10[0], 16);
                           byte[] var15 = Formatter.hexStringToByteArray(var10[1]);
                           if (var15 != null) {
                              var20.put(var14, var15);
                           }
                        } catch (NumberFormatException var17) {
                           this.lm.put(var10[0], var10[1]);
                        }
                  }
               }
            }

            this.oW.q(var20);
         } else if (var4 != 'w' && var4 != 'W' && var4 != 'X') {
            if (var4 != 'O') {
               throw new WI("Unsupported stop-reply payload code: " + var4);
            }

            byte[] var19 = Formatter.hexStringToByteArray(var3);
            this.zz.append(var19);
            Object[] var10000 = new Object[]{Formatter.escapeBytes(var19)};
         } else {
            int var5 = var3.indexOf(59);
            if (var5 < 0) {
               this.xK = MO.nf(var3);
            } else {
               this.xK = MO.nf(var3.substring(0, var5));
            }

            if (var4 != 'w') {
               if (var4 == 'W') {
                  this.gO = "exit";
               } else if (var4 == 'X') {
                  this.gO = "termination";
               }
            }
         }
      }
   }

   public int q() {
      return this.RF;
   }

   public byte[] RF() {
      return this.zz.getAll();
   }

   public int xK() {
      return this.xK;
   }

   public int Dw() {
      return this.Dw;
   }

   public int Uv() {
      return this.Uv;
   }

   public List oW() {
      return this.gP;
   }

   public List gO() {
      return this.za;
   }

   public String nf() {
      return this.gO;
   }

   public Map gP() {
      return this.lm;
   }

   public String q(String var1) {
      return (String)this.lm.get(var1);
   }

   public kW za() {
      return this.oW;
   }

   public boolean lm() {
      return Strings.isContainedIn(this.gO, "termination", "exit");
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.nf != null) {
         var1.append(this.nf + " / ");
      }

      Strings.ff(var1, "%s(%d) / pid=%d / tid=%d / reason=%s", aK.q(this.xK), this.xK, this.Dw, this.Uv, this.gO);
      if (this.oW.Dw() > 0) {
         var1.append(" / ");

         for (Couple var3 : this.oW.Uv()) {
            int var4 = ((RegisterDescriptionEntry)var3.getFirst()).getNumber();
            byte[] var5 = (byte[])var3.getSecond();
            if (var5 != null) {
               Strings.ff(var1, "%d:%s ", var4, RegisterUtil.byteArrayToHex(this.oW.RF(), var5));
            }
         }
      }

      return var1.toString();
   }
}
