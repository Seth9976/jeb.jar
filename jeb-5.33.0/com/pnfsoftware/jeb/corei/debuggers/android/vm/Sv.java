package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.AbstractValueComposite;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueString;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.IAutocompleteListProvider;
import com.pnfsoftware.jeb.util.interpreter.ICommandManager;
import com.pnfsoftware.jeb.util.interpreter.ICommandNode;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jebglobal.Ha;
import com.pnfsoftware.jebglobal.JV;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.dy;
import com.pnfsoftware.jebglobal.gW;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sv extends com.pnfsoftware.jeb.corei.debuggers.android.K {
   static final ILogger kS = GlobalLog.getLogger(com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.class);
   private com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Av wS = new com.pnfsoftware.jeb.corei.debuggers.android.vm.K(this);
   private com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Av UT = new DH(this);
   private com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Av E = new rQ(this);
   private static final String sY = "- "
      + S.L(
         "the way to access an object starting from an Object ID (prefixed by @), this or local variable ('@3', 'this', 'this.foo.bar', '@3.bar', 'this.foo.boo[1]', 'v1[:object].foo')"
      );
   private static final String ys = "- "
      + S.L(
         "an new array with signature + values ('[Z{true,false}', '[Ljava/lang/String{\"foo\",\"bar\"}') or with classical syntax ('new byte[][]{{1, 2},{3, 4}}', 'new java.lang.String[]{\"foo\"}')"
      );
   private static final String ld = "- "
      + S.L("numbers postfixed with their type, except for int and double ('4L' for long, '8B' for byte, '128S' for short', '12.2f' for float)");
   private static final String gp = "- " + S.L("any boolean, character primitives or String ('true', ''a'', '\"foo\"')");
   private static final String oT = "- " + S.L("any new instantiation ('new String(new byte[]{65, 66, 67})', 'new Object()')");
   private final CommandParameter fI = new CommandParameter(
      "objectId", S.L("Object Reference: can be either:\n- the variable vX with its related type separated by ':' ('v0:int')\n") + sY, false, false, this.wS
   );
   private final CommandParameter WR = new CommandParameter(
      "cid",
      S.L(
            "Class ID / Reference Type ID or Object: can be either:\n- the long representing the class prefixed by c ('c3', 'c4523')\n- the signature of the class ('Ljava/lang/String;')\n- the Object ID prefixed by @ ('@3')\n"
         )
         + sY,
      false,
      false,
      this.UT
   );
   private final CommandParameter NS = new CommandParameter(
      "new_value", S.L("New value to set: can be:\n") + sY + "\n" + ys + "\n" + ld + "\n" + gp + "\n" + oT, false, true
   );
   private final CommandParameter vP = new CommandParameter(
      "method",
      S.L(
            "Method ID: the identifier of the method followed by optional arguments separated by commas:\n- the long id of the method ('123')\n- the name of the method ('toString', 'compare this', 'equals 4L', 'equals true', equals \"foo\"\nOptional arguments: \n"
         )
         + sY
         + "\n"
         + ys
         + "\n"
         + ld
         + "\n"
         + gp
         + "\n"
         + oT,
      false,
      true,
      this.E
   );
   private final CommandParameter xC = new CommandParameter("r", "", S.L("Recursively process the superclass until Object class is reached."), true);
   private final CommandParameter ED = new CommandParameter(
      "i", "", S.L("Process the interfaces of a class. Can be combined with -s to recursively process all interfaces of superclasses"), true
   );
   private final CommandParameter Sc = new CommandParameter(
      "b", "", S.L("Provide a brief answer for Objects (do not display all attributes). Display only first line by default"), true
   );
   private final CommandParameter ah = new CommandParameter("t", "threadId", S.L("The thread Id to use. Default Thread by default."), true);
   private final CommandParameter eP = new CommandParameter(
      "f", "frameIndex", S.L("The frame index in the Stack. If not specified, the source frame is the top one."), true
   );
   private qt UO;

   private final long pC(String var1, Mh var2, uX var3) throws IOException, JebException {
      bA var4 = (bA)((Tb)this.pC()).UT();
      IDebuggerVariable var5 = null;
      if (var1.startsWith("this") || var1.startsWith("@")) {
         var5 = this.UO.pC(var1, var2, var3);
      } else if (A(var1)) {
         var5 = this.pC(var2, var3, var1, true);
      }

      if (var5 != null) {
         ITypedValue var6 = var5.getTypedValue();
         if (var6 != null) {
            if (var6 instanceof AbstractValueComposite) {
               return ((AbstractValueComposite)var6).getRefTypeId();
            }

            if (var6 instanceof ValueString) {
               return var4.oT().kS("Ljava/lang/String;");
            }
         }
      } else if (var1.startsWith("c")) {
         var1 = var1.substring(1);
      } else {
         var5 = this.UO.pC(var1, var2, var3);
         if (var5 != null) {
            throw new JebException(this.kS(var1));
         }
      }

      return pC(var4, var1);
   }

   private String kS(String var1) {
      if (var1.contains(".")) {
         var1 = var1.substring(0, var1.indexOf(46));
      }

      return Strings.ff(S.L("The id %s is ambiguous since it can represent both cid or object id. Please use c%s or @%s"), var1, var1, var1);
   }

   private Mh pC(InputToken var1) {
      return this.UO.pC(var1 == null ? null : var1.getValue());
   }

   private uX pC(Mh var1, InputToken var2) {
      return this.UO.pC(var1, var2 == null ? null : var2.getValue());
   }

   private List pC(InputToken[] var1, int var2, Mh var3, uX var4, String[] var5) throws IOException, JebException {
      StringBuilder var6 = new StringBuilder();

      for (int var7 = var2; var7 < var1.length; var7++) {
         var6.append(var1[var7]);
         var6.append(' ');
      }

      return this.UO.pC(var6.toString(), var3, var4, var5);
   }

   public Sv(Tb var1) {
      super(var1);
      this.UO = new qt(var1);
      this.A();
      this.xC();
   }

   @Override
   protected ExecutionResult preCheck() {
      ExecutionResult var1 = super.preCheck();
      if (var1 != null && Strings.isBlank(var1.getMessage())) {
         dy var2 = ((Tb)this.pC()).A();
         String var3;
         if (var2 != null && var2.isAttached() && var2.pC()) {
            var3 = S.L(
               "The native debugger has suspended process execution. You can type \".list\" to list interpreters and \".use $id\" to switch to native console."
            );
         } else if (((Tb)this.pC()).isAttached()) {
            var3 = S.L("The Dalvik VM debugger is attached.");
         } else {
            var3 = S.L("The Dalvik VM debugger is note attached.");
         }

         var1 = ExecutionResult.error(var3);
      }

      return var1;
   }

   private void xC() {
      this.pC(
         new zp(
            this,
            this,
            "frameSlotIndexMode",
            new String[]{"mode"},
            S.L("Set the index type used to retrieve thread frames' variables (AUTO, PAR, VAR)"),
            S.L("Allowed modes: AUTO=automatic (determined from API levels); PAR=Java parameter style indices (pX); VAR=Dalvik variable style indices (vX)")
         )
      );
      this.pC(new KD(this, this, "classes", S.L("List the classes loaded by the VM")));
      this.pC(
         new com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.K(
            this,
            "signature|sig",
            new CommandParameter[]{this.xC, this.ED, this.ah, this.eP, this.WR},
            S.L("Information about a specific class"),
            "",
            new yt(this, false)
         )
      );
      this.pC(
         new com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.K(
            this, "fields", new CommandParameter[]{this.xC, this.ED, this.ah, this.eP, this.WR}, S.L("List the fields of a type"), "", new RC(this, true)
         )
      );
      this.pC(
         new com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.K(
            this, "methods", new CommandParameter[]{this.xC, this.ED, this.ah, this.eP, this.WR}, S.L("List the methods of a type"), "", new sy(this, true)
         )
      );
      this.pC(
         new HE(
               this,
               this,
               "call|invoke",
               new CommandParameter[]{this.ah, this.eP, this.Sc, this.WR, this.vP},
               S.L("invoke a method."),
               S.L(
                  "The method is invoked on a specified object or its class (for static method calls).\nBe carefull that the current thread WILL remain paused, which can cause deadlocks.\nIt searches in object class and in all its superclasses for the best matching method.\nIt can also call any constructor by using the 'new' keyword ('call new Object()')"
               )
            )
            .setOptions(1)
      );
      this.pC(new Ws(this, this, "read|get", new CommandParameter[]{this.ah, this.eP, this.Sc, this.fI}, S.L("Read an object or array."), ""));
      this.pC(
         new bO(
               this,
               this,
               "set",
               new CommandParameter[]{this.ah, this.eP, this.fI, this.NS},
               S.L("Set a method variable or parameter as a typed-value"),
               S.L("Note that the thread must be paused")
            )
            .setOptions(1)
      );
      this.pC(
         new cq(
            this,
            this,
            "pull",
            new CommandParameter[]{
               new CommandParameter("remotePath", S.L("Remote path on the device (source)."), false),
               new CommandParameter("localPath", S.L("Local path (destination)."), false)
            },
            "adb-pull on steroid",
            S.L("A more powerful version of adb-pull (including the use of su-capabilities if the device is rooted)")
         )
      );
   }

   private String pC(String var1, int var2) {
      String[] var3 = Strings.splitLines(var1);
      StringBuilder var4 = new StringBuilder();

      for (int var5 = 0; var5 < var2 && var5 < var3.length; var5++) {
         if (var5 != 0) {
            var4.append("\n");
         }

         var4.append(var3[var5]);
      }

      return var4.toString();
   }

   private Kr pC(Mh var1, uX var2, String var3, boolean var4) throws JebException {
      if (!var1.A()) {
         throw new JebException("Thread is not paused");
      } else if (var2 == null) {
         throw new JebException("Frame is not accessible. Is your thread paused?");
      } else {
         String var5 = null;
         String var6 = null;
         if (var4 || var3.contains(".")) {
            var5 = "object";
            int var7 = var3.indexOf(".");
            if (var7 != -1) {
               var6 = var3.substring(var7 + 1);
               var3 = var3.substring(0, var7);
            }
         }

         String[] var11 = var3.split(":");
         String var8 = var11[0];
         String var9 = var11.length >= 2 ? var11[1] : var5;
         Kr var10 = var2.A(var8, var9);
         if (var10 == null) {
            throw new JebException("No value found in current frame");
         } else {
            return var6 != null ? (Kr)this.UO.pC(var6, var10) : var10;
         }
      }
   }

   static long pC(Ha var0, String var1) {
      long var2 = Conversion.stringToLong(var1);
      if (var2 != 0L) {
         return var2;
      } else {
         List var4 = var0.pC(var1);
         if (var4 != null && !var4.isEmpty()) {
            gW var5 = (gW)var4.get(0);
            return var5 == null ? -1L : var5.A;
         } else {
            return -1L;
         }
      }
   }

   @Override
   protected long pC(String var1) {
      return -1L;
   }

   @Override
   public AutocompletionResult pC(List var1, ICommandNode var2) {
      if (var2 instanceof AbstractCommandHandler) {
         try {
            InputToken[] var3 = ((AbstractCommandHandler)var2).parseInputToken(var1, false);
            List var4 = ((AbstractCommandHandler)var2).getParameters();
            InputToken var5 = (InputToken)var1.get(var1.size() - 1);
            if (var5.getValue().startsWith("-")) {
               return null;
            }

            for (int var6 = var3.length - 1; var6 >= 0; var6--) {
               if (var3[var6] != null) {
                  int var7 = var6;
                  int var8 = 0;
                  if (var6 >= var4.size()) {
                     var7 = var4.size() - 1;
                     var8 = var6 - var4.size() + 1;
                  }

                  IAutocompleteListProvider var9 = ((CommandParameter)var4.get(var7)).getAutocompleteProvider();
                  if (var9 != null) {
                     if (var9 instanceof com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Av) {
                        return ((com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Av)var9).pC(var5.getValue(), var8, (AbstractCommandHandler)var2, var3);
                     }

                     var9.getAutocompleteList(var5.getValue());
                  }
               }
            }
         } catch (Exception var10) {
            return null;
         }
      }

      return null;
   }

   static boolean A(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         char var1 = Character.toLowerCase(var0.charAt(0));
         return var1 == 'v' || var1 == 'p';
      } else {
         return false;
      }
   }

   private abstract static class Av implements IAutocompleteListProvider {
      public abstract AutocompletionResult pC(String var1, int var2, AbstractCommandHandler var3, InputToken[] var4) throws JebException;

      @Override
      public AutocompletionResult getAutocompleteList(String var1) {
         try {
            return this.pC(var1, 0, null, null);
         } catch (JebException var2) {
            return null;
         }
      }
   }

   private class K extends AbstractCommandHandler {
      private com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Sv A;

      public K(ICommandManager var2, String var3, CommandParameter[] var4, String var5, String var6, com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.Sv var7) {
         super(var2, var3, var4, var5, var6);
         this.A = var7;
      }

      @Override
      public ExecutionResult execute(List var1) {
         try {
            InputToken[] var2 = this.parseInputToken(var1);
            boolean var3 = var2[0] != null;
            boolean var4 = var2[1] != null;
            Mh var5 = Sv.this.pC(var2[2]);
            uX var6 = Sv.this.pC(var5, var2[3]);
            long var7 = Sv.this.pC(var2[4].getValue(), var5, var6);
            return var7 < 0L ? ExecutionResult.error(S.L("Invalid Class id")) : this.A.pC(var3, var4, var7);
         } catch (Exception var9) {
            com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.kS.catching(var9);
            return ExecutionResult.error(var9);
         }
      }
   }

   private abstract class Sv {
      private boolean pC;

      public Sv(boolean var2) {
         this.pC = var2;
      }

      protected bA pC() {
         return (bA)((Tb)Sv.this.pC()).UT();
      }

      public ExecutionResult pC(boolean var1, boolean var2, long var3) {
         try {
            StringBuilder var5 = this.pC(var1, var2, var3, 0, new HashMap());
            return ExecutionResult.fromObject(var5);
         } catch (Exception var6) {
            com.pnfsoftware.jeb.corei.debuggers.android.vm.Sv.kS.catching(var6);
            return ExecutionResult.error(var6);
         }
      }

      public StringBuilder pC(boolean var1, boolean var2, long var3, int var5, Map var6) throws IOException, JebException {
         StringBuilder var7 = new StringBuilder();
         if (var3 == 0L) {
            return var7;
         } else {
            Object var8 = this.pC(var3);
            StringBuilder var9 = new StringBuilder();
            if (var8 != null) {
               if (var5 >= 1) {
                  for (int var10 = 0; var10 < var5; var10++) {
                     if (var5 - 1 == var10) {
                        var9.append("|- ");
                     } else if (Booleans.toBoolean((Boolean)var6.get(var10))) {
                        var9.append("|  ");
                     } else {
                        var9.append("   ");
                     }
                  }
               }

               if (this.pC) {
                  JV var16 = this.pC().oT().A(var3);
                  var7.append((CharSequence)var9).append(var16).append('\n');
               }

               String var17 = var8.toString();
               String[] var11 = Strings.splitLines(var17);

               for (String var15 : var11) {
                  var7.append((CharSequence)var9).append(var15).append('\n');
               }

               ArrayList var18 = new ArrayList();
               if (var1) {
                  long var19 = this.pC().oT().sY(var3);
                  var18.add(var19);
               }

               if (var2) {
                  List var20 = this.pC().oT().ys(var3);
                  var18.addAll(var20);
               }

               var6.put(var5, true);

               for (int var21 = 0; var21 < var18.size(); var21++) {
                  if (var21 == var18.size() - 1) {
                     var6.put(var5, false);
                  }

                  long var22 = (Long)var18.get(var21);
                  var7.append((CharSequence)this.pC(var1, var2, var22, var5 + 1, var6));
               }

               var6.put(var5, false);
            }

            return var7;
         }
      }

      protected abstract Object pC(long var1) throws IOException, JebException;
   }
}
