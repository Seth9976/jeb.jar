package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.Ht;
import com.pnfsoftware.jebglobal.IW;
import com.pnfsoftware.jebglobal.JG;
import com.pnfsoftware.jebglobal.oH;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class LinuxDbgClient {
   private static final ILogger RF = GlobalLog.getLogger();
   static final boolean q = true;
   private oH xK;

   public static void main(String[] var0) throws Exception {
      LinuxDbgClient var1 = new LinuxDbgClient("192.168.91.131", 4400);
      oH var2 = var1.xK();
      String var3 = "";

      while (true) {
         try {
            System.out.print("> ");
            String var4 = q().trim();
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
                  RF.error("Debugger not connected");
               } else {
                  if (var6.equals("pause")) {
                     throw new RuntimeException("Pausing must be done on the target system!");
                  }

                  if (var6.equals("kill")) {
                     var2.q(true);
                     break;
                  }

                  if (var6.equals("detach")) {
                     var2.q(false);
                     break;
                  }

                  if (!var2.oW()) {
                     RF.error("The target is running... in all-stop mode, GDB is now expected a response! (The request was not sent)");
                  } else if (var6.equals("info")) {
                     RF.info("Status: %s", var2.oQ().Dw());
                  } else if (var6.equals("r")) {
                     RF.info("Registers: %s", var2.EB());
                  } else if (var6.equals("wrt")) {
                     Ht var7 = var2.EB();
                     long var8 = var7.getValueAsLong(0);
                     var7.setValueAsLong(0, 100L);
                     if (!var2.q(var7)) {
                        RF.info("failed writing registers");
                     } else {
                        var7 = var2.EB();
                        long var10 = var7.getValueAsLong(0);
                        if (var10 != 100L) {
                           RF.info("it looks like register #0 was not updated properly: %Xh", var10);
                        } else {
                           var7.setValueAsLong(0, var8);
                           var2.q(var7);
                           RF.info("PASSED!");
                        }
                     }
                  } else if (var6.equals("rrt")) {
                     if (var2.nf() == ProcessorType.X86_64) {
                        Ht var21 = var2.EB();
                        RegisterDescriptionEntry var29 = var21.q().getDescriptionEntryByName("rflags");
                        if (var29 == null) {
                           var29 = var21.q().getDescriptionEntryByName("eflags");
                        }

                        Object[] var10000 = new Object[]{var29};
                        long var9 = var21.getValueAsLong(var29.getNumber());
                        var10000 = new Object[]{var9};
                        var21.setValueAsLong(var29.getNumber(), -4294966784L);
                        var2.q(var21);
                        RF.info("PASSED!");
                     }
                  } else if (var6.equals("c") || var6.equals("continue")) {
                     if (var5.length >= 2) {
                        int var30 = Integer.parseInt(var5[1], 16);
                        var2.oQ().xK(var30);
                     } else {
                        boolean var28 = var2.Dw();
                        RF.info("%b", var28);
                     }
                  } else if (var6.equals("step") || var6.equals("stepi")) {
                     boolean var27 = var2.Xo();
                     RF.info("%b", var27);
                  } else if (var6.equals("stepo")) {
                     boolean var22 = var2.Bu();
                     RF.info("%b", var22);
                  } else if (var6.equals("stop")) {
                     var2.oQ().nf();
                  } else if (var6.equals("supported")) {
                     RF.info("Supported:\n%s", var2.HF());
                  } else if (var6.equals("lldb")) {
                     RF.info("Host Info: %s", var2.LK());
                     RF.info("Server Info: %s", var2.io());
                     RF.info("Target Process Info: %s", var2.qa());
                     RF.info("Target Registers Layout: %s", var2.Hk());
                  } else if (var6.equals("osdata")) {
                     RF.info(var2.oQ().xK());
                  } else if (var6.equals("nonstop")) {
                     RF.info("%b", var2.RF(true));
                  } else if (var6.equals("allstop")) {
                     RF.info("%b", var2.RF(false));
                  } else if (var6.equals("find")) {
                     if (var5.length >= 4) {
                        long var23 = Conversion.stringToLong(var5[1]);
                        long var31 = Conversion.stringToLong(var5[2]);
                        byte[] var11 = Formatter.hexStringToByteArray(var5[3]);
                        if (var11 != null) {
                           long var12 = var2.Ef().findBytes(var23, var31, var11);
                           if (var12 >= 0L) {
                              long var14 = var23 + var12;
                              RF.info("Found at %Xh", var14);
                              byte[] var16 = new byte[32];
                              int var17 = var2.Ef().read(var14, var16.length, var16, 0);
                              if (var17 > 0) {
                                 RF.info("%s", Formatter.formatBinaryBlock(var16, 0, var17, -var14, true));
                              }
                           }
                        }
                     }
                  } else if (var4.startsWith("exec:")) {
                     String var24 = var2.oQ().q(var4.substring(5));
                     RF.info(var24);
                  } else if (var4.startsWith("raw:")) {
                     byte[] var25 = var2.Dw(var4.substring(4).trim());
                     if (var25 != null) {
                        RF.info(Strings.decodeLocal(var25));
                     }
                  } else {
                     ExecutionResult var26 = var2.sH().execute(var4);
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
         } catch (JG | IW var18) {
            RF.catching(var18);
         } catch (Exception var19) {
            RF.catching(var19);
            break;
         }
      }

      var1.RF();
      RF.info("Done.");
      System.exit(0);
   }

   static String q() {
      BufferedReader var0 = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));

      try {
         return var0.readLine();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public LinuxDbgClient(String var1, int var2) {
      this.xK = new oH(var1, var2);
      this.xK.xK();
      this.xK.q(new eo(this));
   }

   public void RF() {
      if (this.xK != null) {
         if (this.xK.oW()) {
            this.xK.Dw();
         }

         this.xK.q(false);
         this.xK = null;
      }
   }

   public oH xK() {
      return this.xK;
   }

   static {
      GlobalLog.addDestinationStream(System.out);
   }
}
