package com.pnfsoftware.jeb.util.logging;

import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;

class SimpleLogger implements ILogger {
   private String name;
   private int cutoffLevel;

   public SimpleLogger(String var1, int var2) {
      this.name = var1;
      this.cutoffLevel = var2;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public void setEnabledLevel(int var1) {
      this.cutoffLevel = var1;
   }

   @Override
   public int getEnabledLevel() {
      return this.cutoffLevel;
   }

   @Override
   public void log(int var1, boolean var2, String var3, Object... var4) {
      if (var1 != Integer.MAX_VALUE) {
         if (var1 >= 60 || var1 >= this.cutoffLevel && var1 >= GlobalLog.getCutoffLevel() && !GlobalLog.isGloballyDisabled(this)) {
            if (var1 == 100) {
               if (GlobalLog.outStatusSinks.isEmpty()) {
                  return;
               }
            } else if (GlobalLog.outStreams.isEmpty() && GlobalLog.outBuffers.isEmpty()) {
               return;
            }

            StringBuilder var5 = new StringBuilder();
            if (var4.length == 0) {
               var5.append(var3);
            } else {
               try {
                  Strings.ff(Locale.US, var5, var3, var4);
               } catch (IllegalFormatException | NullPointerException var17) {
                  var5 = new StringBuilder(var17.getMessage());
               }
            }

            if (var1 == 100) {
               for (LogStatusSink var24 : GlobalLog.outStatusSinks) {
                  var24.update(var5);
               }
            } else {
               if (!var2) {
                  if (GlobalLog.prefixWithLevel) {
                     char var6 = GlobalLog.getPrefixLetterForLevel(var1);
                     if (var6 != 0) {
                        String var7 = "[" + var6 + "] ";
                        int var25 = var5.length();

                        while (true) {
                           var25 = var5.lastIndexOf("\n", var25);
                           if (var25 < 0) {
                              var5.insert(0, var7);
                              break;
                           }

                           var5.insert(var25 + 1, var7);
                           var25--;
                        }
                     }
                  }

                  var5.append("\n");
               }

               StringBuilder var19 = null;
               if (this.needTimestamp()) {
                  Calendar var21 = Calendar.getInstance();
                  String var26 = Strings.ff(
                     "%04d-%02d-%02d %02d:%02d:%02d.%03d %s",
                     var21.get(1),
                     1 + var21.get(2),
                     var21.get(5),
                     var21.get(11),
                     1 + var21.get(12),
                     var21.get(13),
                     var21.get(14),
                     var21.getTimeZone().getDisplayName(false, 0)
                  );
                  var19 = new StringBuilder("[").append(var26).append("] ").append((CharSequence)var5);
               }

               for (StreamSink var27 : GlobalLog.outStreams) {
                  if (var1 >= 60 || var1 >= var27.getLogLevel()) {
                     OutputStream var9 = (OutputStream)var27.getOutput();
                     StringBuilder var10 = var27.isPrefixWithTimestamp() ? var19 : var5;

                     try {
                        char[] var11 = new char[var10.length()];
                        var10.getChars(0, var10.length(), var11, 0);
                        if (var9 instanceof PrintStream var12) {
                           if (var11.length > 16384) {
                              char[] var14 = new char[16384];

                              for (short var15 = 0; var15 < var11.length; var15 += 16384) {
                                 int var16 = var11.length - var15;
                                 if (var16 >= 16384) {
                                    System.arraycopy(var11, var15, var14, 0, 16384);
                                 } else {
                                    var14 = new char[var16];
                                    System.arraycopy(var11, var15, var14, 0, var16);
                                 }

                                 var12.print(var14);
                                 var12.flush();
                              }
                           } else {
                              var12.print(var11);
                           }
                        } else {
                           byte[] var13 = CharSequences.toByteArray(var11);
                           var9.write(var13);
                        }
                     } catch (IOException var18) {
                     }
                  }
               }

               for (BufferSink var28 : GlobalLog.outBuffers) {
                  if (var1 >= 60 || var1 >= var28.getLogLevel()) {
                     List var29 = (List)var28.getOutput();
                     StringBuilder var30 = var28.isPrefixWithTimestamp() ? var19 : var5;
                     var29.add(var30);
                  }
               }
            }
         }
      }
   }

   private boolean needTimestamp() {
      for (Sink var2 : GlobalLog.getRegularSinks()) {
         if (var2.isPrefixWithTimestamp()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void i(String var1, Object... var2) {
   }

   @Override
   public void trace(String var1, Object... var2) {
      this.log(10, false, var1, var2);
   }

   @Override
   public void fine(String var1, Object... var2) {
      this.trace(var1, var2);
   }

   @Override
   public void debug(String var1, Object... var2) {
      this.log(20, false, var1, var2);
   }

   @Override
   public void info(String var1, Object... var2) {
      this.log(30, false, var1, var2);
   }

   @Override
   public void warn(String var1, Object... var2) {
      this.log(40, false, var1, var2);
   }

   @Override
   public void warning(String var1, Object... var2) {
      this.warn(var1, var2);
   }

   @Override
   public void error(String var1, Object... var2) {
      this.log(50, false, var1, var2);
   }

   @Override
   public void severe(String var1, Object... var2) {
      this.error(var1, var2);
   }

   @Override
   public void catching(Throwable var1) {
      GlobalLog.catching(this, var1);
   }

   @Override
   public void catching(Throwable var1, String var2) {
      GlobalLog.catching(this, var1, var2);
   }

   @Override
   public void catching(Throwable var1, String var2, Object... var3) {
      GlobalLog.catching(this, var1, Strings.ff(var2, var3));
   }

   @Override
   public void catchingSilent(Throwable var1) {
      GlobalLog.catchingDebug(this, var1);
   }

   @Override
   public void status(String var1, Object... var2) {
      this.log(100, true, var1, var2);
   }
}
