package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.BD;
import com.pnfsoftware.jebglobal.LD;
import com.pnfsoftware.jebglobal.RD;
import com.pnfsoftware.jebglobal.aI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LinuxDbgClient {
   private static final ILogger pC = GlobalLog.getLogger();
   private aI A;

   public static void main(String[] var0) throws Exception {
      LinuxDbgClient var1 = new LinuxDbgClient("192.168.91.131", 4400);
      aI var2 = var1.kS();
      String var3 = "";

      while (true) {
         try {
            System.out.print("> ");
            String var4 = pC().trim();
            if (var4.isEmpty()) {
               var4 = var3;
            } else {
               var3 = var4;
            }

            String[] var5 = var4.split("\\s+");
            if (var5.length != 0) {
               String var6 = var5[0];
               if (var6.equals("exit")) {
                  break;
               }

               if (var2 == null) {
                  pC.error("Debugger not connected");
               } else {
                  if (var6.equals("pause")) {
                     throw new RuntimeException("Pausing must be done on the target system!");
                  }

                  if (var6.equals("kill")) {
                     var2.pC(true);
                     break;
                  }

                  if (var6.equals("detach")) {
                     var2.pC(false);
                     break;
                  }

                  if (!var2.UT()) {
                     pC.error("The target is running... in all-stop mode, GDB is now expected a response! (The request was not sent)");
                  } else if (var6.equals("info")) {
                     pC.info("Status: %s", var2.eP().wS());
                  } else if (var6.equals("r")) {
                     pC.info("Registers: %s", var2.Cu());
                  } else if (var6.equals("wrt")) {
                     LD var7 = var2.Cu();
                     long var8 = var7.getValueAsLong(0);
                     var7.setValueAsLong(0, 100L);
                     if (!var2.pC(var7)) {
                        pC.info("failed writing registers");
                     } else {
                        var7 = var2.Cu();
                        long var10 = var7.getValueAsLong(0);
                        if (var10 != 100L) {
                           pC.info("it looks like register #0 was not updated properly: %Xh", var10);
                        } else {
                           var7.setValueAsLong(0, var8);
                           var2.pC(var7);
                           pC.info("PASSED!");
                        }
                     }
                  } else if (var6.equals("rrt")) {
                     if (var2.sY() == ProcessorType.X86_64) {
                        LD var21 = var2.Cu();
                        RegisterDescriptionEntry var29 = var21.pC().getDescriptionEntryByName("rflags");
                        if (var29 == null) {
                           var29 = var21.pC().getDescriptionEntryByName("eflags");
                        }

                        Object[] var10000 = new Object[]{var29};
                        long var9 = var21.getValueAsLong(var29.getNumber());
                        var10000 = new Object[]{var9};
                        var21.setValueAsLong(var29.getNumber(), -4294966784L);
                        var2.pC(var21);
                        pC.info("PASSED!");
                     }
                  } else if (var6.equals("c") || var6.equals("continue")) {
                     if (var5.length >= 2) {
                        int var30 = Integer.parseInt(var5[1], 16);
                        var2.eP().kS(var30);
                     } else {
                        boolean var28 = var2.kS();
                        pC.info("%b", var28);
                     }
                  } else if (var6.equals("step") || var6.equals("stepi")) {
                     boolean var27 = var2.hZ();
                     pC.info("%b", var27);
                  } else if (var6.equals("stepo")) {
                     boolean var22 = var2.UW();
                     pC.info("%b", var22);
                  } else if (var6.equals("stop")) {
                     var2.eP().ys();
                  } else if (var6.equals("supported")) {
                     pC.info("Supported:\n%s", var2.WR());
                  } else if (var6.equals("lldb")) {
                     pC.info("Host Info: %s", var2.NS());
                     pC.info("Server Info: %s", var2.vP());
                     pC.info("Target Process Info: %s", var2.xC());
                     pC.info("Target Registers Layout: %s", var2.ED());
                  } else if (var6.equals("osdata")) {
                     pC.info(var2.eP().kS());
                  } else if (var6.equals("nonstop")) {
                     pC.info("%b", var2.A(true));
                  } else if (var6.equals("allstop")) {
                     pC.info("%b", var2.A(false));
                  } else if (var6.equals("find")) {
                     if (var5.length >= 4) {
                        long var23 = Conversion.stringToLong(var5[1]);
                        long var31 = Conversion.stringToLong(var5[2]);
                        byte[] var11 = Formatter.hexStringToByteArray(var5[3]);
                        if (var11 != null) {
                           long var12 = var2.z().findBytes(var23, var31, var11);
                           if (var12 >= 0L) {
                              long var14 = var23 + var12;
                              pC.info("Found at %Xh", var14);
                              byte[] var16 = new byte[32];
                              int var17 = var2.z().read(var14, var16.length, var16, 0);
                              if (var17 > 0) {
                                 pC.info("%s", Formatter.formatBinaryBlock(var16, 0, var17, -var14, true));
                              }
                           }
                        }
                     }
                  } else if (var4.startsWith("exec:")) {
                     String var24 = var2.eP().pC(var4.substring(5));
                     pC.info(var24);
                  } else if (var4.startsWith("raw:")) {
                     byte[] var25 = var2.wS(var4.substring(4).trim());
                     if (var25 != null) {
                        pC.info(Strings.decodeLocal(var25));
                     }
                  } else {
                     ExecutionResult var26 = var2.hK().execute(var4);
                     if (var26 != null) {
                        if (var26.getCode() != 0) {
                           Object[] var33 = new Object[]{var26.getCode()};
                        }

                        var26.getMessage();
                        Object[] var34 = new Object[0];
                     }
                  }
               }
            }
         } catch (RD | BD var18) {
            pC.catching(var18);
         } catch (Exception var19) {
            pC.catching(var19);
            break;
         }
      }

      var1.A();
      pC.info("Done.");
      System.exit(0);
   }

   static String pC() {
      BufferedReader var0 = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

      try {
         return var0.readLine();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public LinuxDbgClient(String var1, int var2) {
      this.A = new aI(var1, var2);
      this.A.gp();
      this.A.pC(new Av(this));
   }

   public void A() {
      if (this.A != null) {
         if (this.A.UT()) {
            this.A.kS();
         }

         this.A.pC(false);
         this.A = null;
      }
   }

   public aI kS() {
      return this.A;
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
