package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitInterpreter;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerOperationType;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThread;
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

public abstract class nI extends SimpleCommandManager implements IUnitInterpreter {
   private static final ILogger xK = GlobalLog.getLogger(nI.class);
   protected static final ExecutionResult q = ExecutionResult.error(S.L("There is no default thread"));
   protected static final ExecutionResult RF = ExecutionResult.error(S.L("The default thread is not suspended"));
   private final CommandParameter Dw = new CommandParameter("tid", S.L("Thread Id"), true);
   private IDebuggerUnit Uv;
   private String oW = "";
   private Map gO;

   public nI(IDebuggerUnit var1) {
      this.Uv = var1;
   }

   @Override
   public IPluginInformation getPluginInformation() {
      return null;
   }

   @Override
   public boolean isTarget(IUnit var1) {
      if (var1 == this.q()) {
         return true;
      } else {
         for (ICodeUnit var3 : this.Uv.getPotentialDebuggees()) {
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
      return this.q();
   }

   protected IDebuggerUnit q() {
      return this.Uv;
   }

   @Override
   public String getName() {
      return this.Uv.getName();
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
      return !this.Uv.canPerformOperation(DebuggerOperationType.UNKNOWN) ? ExecutionResult.GENERIC_ERROR : null;
   }

   @Override
   public ExecutionResult executeCommand(String var1) {
      if (var1 == null) {
         var1 = "";
      } else if (var1.trim().isEmpty()) {
         var1 = this.oW;
      }

      ExecutionResult var2 = super.execute(var1);
      if (var2 == null) {
         this.oW = "";
         return ExecutionResult.GENERIC_ERROR;
      } else {
         this.oW = var1;
         return var2;
      }
   }

   protected void RF() {
      this.q(this.xK());
      this.q(this.Dw());
      this.q(this.Uv());
      this.q(this.oW());
      this.q(this.gO());
      this.q(this.nf());
      this.q(this.gP());
      this.q(this.za());
      this.q(this.lm());
      this.q(this.zz());
      this.q(this.JY());
      this.q(this.HF());
      this.q(this.LK());
   }

   protected void q(AbstractCommandHandler var1) {
      if (var1 != null && this.addChild(var1) == null) {
         xK.error(S.L("The handler {%s} could not be registered successfully"));
      }
   }

   public AbstractCommandHandler xK() {
      return new ej(this, this, "info", S.L("Display basic information about the debuggee"));
   }

   public AbstractCommandHandler Dw() {
      return new EE(this, this, "libs|modules", S.L("Display information about the target modules")).addParameter(new CommandParameter("name-filter", true));
   }

   public AbstractCommandHandler Uv() {
      return new qV(this, this, "resume", S.L("Run or resume the target, a thread, or the default thread (tid 0)")).addParameter(this.Dw);
   }

   public AbstractCommandHandler oW() {
      return new vn(this, this, "pause", S.L("Pause the target"));
   }

   public AbstractCommandHandler gO() {
      return new PY(this, this, "detach", S.L("Detach the target (if possible)"));
   }

   public AbstractCommandHandler nf() {
      return new vb(this, this, "kill|terminate", S.L("Kill the target (unstable)"));
   }

   public AbstractCommandHandler gP() {
      return new oL(this, this, "threads", S.L("List the process threads"));
   }

   public AbstractCommandHandler za() {
      return new Vj(this, this, "thread", S.L("Set or get the default thread")).addParameter(this.Dw);
   }

   public AbstractCommandHandler lm() {
      return new Bu(this, this, "step|stepi", S.L("Step \"into\" one instruction in the default thread"));
   }

   public AbstractCommandHandler zz() {
      return new oM(this, this, "stepo", S.L("Step \"over\" one instruction in the default thread"));
   }

   public AbstractCommandHandler JY() {
      return new Nt(this, this, "stepu", S.L("Step \"up\"/\"out\" (run until return) in the default thread"));
   }

   public AbstractCommandHandler HF() {
      return new iZ(this, this, "b|bp", S.L("Set or list breakpoints")).addParameter(new CommandParameter("address", true));
   }

   public AbstractCommandHandler LK() {
      return new tw(this, this, "bc", S.L("Clear one or all breakpoints")).addParameter(new CommandParameter("index", true));
   }

   protected abstract long q(String var1);

   protected long io() {
      IDebuggerThread var1 = this.Uv.getDefaultThread();
      return var1 == null ? 0L : var1.getId();
   }

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

         this.q(var2, var4, var14);
      } else {
         for (ICommandNode var17 : var2) {
            String[] var8 = var17.getName().split("\\|");

            for (String var12 : var8) {
               if (!Strings.isBlank(var12) && var12.equals(var14)) {
                  return this.q(var3.subList(1, var3.size()), var17);
               }
            }
         }

         if (var14.equals("help") && var3.size() == 2) {
            var14 = ((InputToken)var3.get(1)).getValue();
            this.q(var2, var4, var14);
         }
      }

      return var4;
   }

   private void q(List var1, AutocompletionResult var2, String var3) {
      for (ICommandNode var5 : var1) {
         String[] var6 = var5.getName().split("\\|");

         for (String var10 : var6) {
            if (!Strings.isBlank(var10) && var10.startsWith(var3)) {
               var2.add(var10);
            }
         }
      }
   }

   public abstract AutocompletionResult q(List var1, ICommandNode var2);

   @Override
   public void setData(Object var1, Object var2) {
      if (this.gO == null) {
         this.gO = new HashMap();
      }

      this.gO.put(var1, var2);
   }

   @Override
   public Object getData(Object var1) {
      return this.gO == null ? null : this.gO.get(var1);
   }

   @Override
   public void dispose() {
   }
}
