package com.pnfsoftware.jeb.client.api;

import com.pnfsoftware.jeb.core.output.IItem;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.graph.Digraph;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Callable;

public interface IGraphicalClientContext extends IClientContext {
   String displayFileOpenSelector(String var1);

   String displayFileSaveSelector(String var1);

   String displayFolderSelector(String var1);

   String displayQuestionBox(String var1, String var2, String var3);

   String[] displaySimpleForm(String var1, String var2, String... var3);

   Object[] displayForm(String var1, String var2, FormEntry... var3);

   int displayMessageBox(String var1, String var2, IconType var3, ButtonGroupType var4);

   String displayText(String var1, String var2, boolean var3);

   int displayList(String var1, String var2, String[] var3, Object[][] var4);

   default void displayGraph(String var1, Digraph var2) {
      this.displayGraph(var1, var2, null);
   }

   void displayGraph(String var1, Digraph var2, GraphDialogExtensions var3);

   void executeAsync(String var1, Runnable var2) throws InterruptedException, InvocationTargetException;

   Object executeAsyncWithReturn(String var1, Callable var2) throws InterruptedException, InvocationTargetException;

   void uiExecute(Runnable var1);

   void uiExecuteBlocking(Runnable var1);

   void uiExecuteWithDelay(int var1, Runnable var2);

   void registerUnitFragmentPositionChangeListener(IUnitFragmentPositionChangeListener var1);

   void unregisterUnitFragmentPositionChangeListener(IUnitFragmentPositionChangeListener var1);

   List getViews(IUnit var1);

   List getViews();

   IUnitView getFocusedView();

   default IUnitFragment getFocusedFragment() {
      IUnitView var1 = this.getFocusedView();
      return var1 == null ? null : var1.getActiveFragment();
   }

   default IUnit getFocusedUnit() {
      IUnitView var1 = this.getFocusedView();
      return var1 == null ? null : var1.getUnit();
   }

   default String getFocusedAddress() {
      IUnitFragment var1 = this.getFocusedFragment();
      return var1 == null ? null : var1.getActiveAddress();
   }

   default IItem getFocusedItem() {
      IUnitFragment var1 = this.getFocusedFragment();
      return var1 == null ? null : var1.getActiveItem();
   }

   boolean openView(IUnit var1);

   IUnitFragment findFragment(IUnit var1, String var2, boolean var3);

   boolean navigate(IUnit var1, String var2);
}
