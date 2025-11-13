package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitInterpreter;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerOperationType;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerUnit;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.interpreter.AbstractCommandHandler;
import com.pnfsoftware.jeb.util.interpreter.AutocompletionResult;
import com.pnfsoftware.jeb.util.interpreter.CommandParameter;
import com.pnfsoftware.jeb.util.interpreter.ExecutionResult;
import com.pnfsoftware.jeb.util.interpreter.ICommandNode;
import com.pnfsoftware.jeb.util.interpreter.InputToken;
import com.pnfsoftware.jeb.util.interpreter.SimpleCommandManager;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class K extends SimpleCommandManager implements IUnitInterpreter {
   private static final ILogger kS = GlobalLog.getLogger(K.class);
   protected static final ExecutionResult pC = ExecutionResult.error(S.L("There is no default thread"));
   protected static final ExecutionResult A = ExecutionResult.error(S.L("The default thread is not suspended"));
   private final CommandParameter wS = new CommandParameter("tid", S.L("Thread Id"), true);
   private IDebuggerUnit UT;
   private String E = "";
   private Map sY;

   public K(IDebuggerUnit var1) {
      this.UT = var1;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return null;
   }

   @Override
   public boolean isTarget(IUnit var1) {
      if (var1 == this.pC()) {
         return true;
      } else {
         for (ICodeUnit var3 : this.UT.getPotentialDebuggees()) {
            if (var3 == var1) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void setPrimaryTarget(IUnit var1) {
   }

   @Override
   public IUnit getPrimaryTarget() {
      return this.pC();
   }

   protected IDebuggerUnit pC() {
      return this.UT;
   }

   @Override
   public String getName() {
      return this.UT.getName();
   }

   @Override
   public String getDescription() {
      return Strings.ff(S.L("Debugger interpreter for %s"), this.getName());
   }

   @Override
   public String getBanner() {
      return null;
   }

   @Override
   public void prepare() {
   }

   @Override
   public boolean shouldDisplayRawResults() {
      return false;
   }

   @Override
   protected ExecutionResult preCheck() {
      return !this.UT.canPerformOperation(DebuggerOperationType.UNKNOWN) ? ExecutionResult.GENERIC_ERROR : null;
   }

   @Override
   public ExecutionResult executeCommand(String var1) {
      if (var1 == null) {
         var1 = "";
      } else if (var1.trim().isEmpty()) {
         var1 = this.E;
      }

      ExecutionResult var2 = super.execute(var1);
      if (var2 == null) {
         this.E = "";
         return ExecutionResult.GENERIC_ERROR;
      } else {
         this.E = var1;
         return var2;
      }
   }

   protected void A() {
      this.pC(this.kS());
      this.pC(this.wS());
      this.pC(this.UT());
      this.pC(this.E());
      this.pC(this.sY());
      this.pC(this.ys());
      this.pC(this.ld());
      this.pC(this.gp());
      this.pC(this.oT());
      this.pC(this.fI());
      this.pC(this.WR());
      this.pC(this.NS());
      this.pC(this.vP());
   }

   protected void pC(AbstractCommandHandler var1) {
      if (var1 != null && this.addChild(var1) == null) {
         kS.error(S.L("The handler {%s} could not be registered successfully"));
      }
   }

   public AbstractCommandHandler kS() {
      return new Ws(this, this, "info", S.L("Display basic information about the debuggee"));
   }

   public AbstractCommandHandler wS() {
      return new zp(this, this, "libs|modules", S.L("Display information about the target modules")).addParameter(new CommandParameter("name-filter", true));
   }

   public AbstractCommandHandler UT() {
      return new KD(this, this, "resume", S.L("Run or resume the target, a thread, or the default thread (tid 0)")).addParameter(this.wS);
   }

   public AbstractCommandHandler E() {
      return new yt(this, this, "pause", S.L("Pause the target"));
   }

   public AbstractCommandHandler sY() {
      return new RC(this, this, "detach", S.L("Detach the target (if possible)"));
   }

   public AbstractCommandHandler ys() {
      return new sy(this, this, "kill|terminate", S.L("Kill the target (unstable)"));
   }

   public AbstractCommandHandler ld() {
      return new HE(this, this, "threads", S.L("List the process threads"));
   }

   public AbstractCommandHandler gp() {
      return new qt(this, this, "thread", S.L("Set or get the default thread")).addParameter(this.wS);
   }

   public AbstractCommandHandler oT() {
      return new oP(this, this, "step|stepi", S.L("Step \"into\" one instruction in the default thread"));
   }

   public AbstractCommandHandler fI() {
      return new bO(this, this, "stepo", S.L("Step \"over\" one instruction in the default thread"));
   }

   public AbstractCommandHandler WR() {
      return new cq(this, this, "stepu", S.L("Step \"up\"/\"out\" (run until return) in the default thread"));
   }

   public AbstractCommandHandler NS() {
      return new DH(this, this, "b|bp", S.L("Set or list breakpoints")).addParameter(new CommandParameter("address", true));
   }

   public AbstractCommandHandler vP() {
      return new rQ(this, this, "bc", S.L("Clear one or all breakpoints")).addParameter(new CommandParameter("index", true));
   }

   protected abstract long pC(String var1);

   @Override
   public AutocompletionResult autoComplete(String var1) {
      List var2 = this.getChildren();

      Object var3;
      try {
         var3 = this.parseTokenString(var1);
      } catch (Exception var13) {
         List var5 = Arrays.asList(var1.split(" "));
         var3 = new ArrayList();

         for (String var7 : var5) {
            var3.add(new InputToken(var7));
         }
      }

      if (var1.endsWith(" ")) {
         var3.add(new InputToken(""));
      }

      AutocompletionResult var4 = new AutocompletionResult();
      String var14 = var3.isEmpty() ? "" : ((InputToken)var3.get(0)).getValue();
      if (var3.size() <= 1) {
         if ("help".startsWith(var14)) {
            var4.add("help");
         }

         this.pC(var2, var4, var14);
      } else {
         for (ICommandNode var17 : var2) {
            String[] var8 = var17.getName().split("\\|");

            for (String var12 : var8) {
               if (!Strings.isBlank(var12) && var12.equals(var14)) {
                  return this.pC(var3.subList(1, var3.size()), var17);
               }
            }
         }

         if (var14.equals("help") && var3.size() == 2) {
            var14 = ((InputToken)var3.get(1)).getValue();
            this.pC(var2, var4, var14);
         }
      }

      return var4;
   }

   private void pC(List var1, AutocompletionResult var2, String var3) {
      for (ICommandNode var5 : var1) {
         String[] var6 = var5.getName().split("\\|");

         for (String var10 : var6) {
            if (!Strings.isBlank(var10) && var10.startsWith(var3)) {
               var2.add(var10);
            }
         }
      }
   }

   public abstract AutocompletionResult pC(List var1, ICommandNode var2);

   @Override
   public void setData(Object var1, Object var2) {
      if (this.sY == null) {
         this.sY = new HashMap();
      }

      this.sY.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.sY == null ? null : this.sY.get(var1);
   }

   @Override
   public void dispose() {
   }
}
