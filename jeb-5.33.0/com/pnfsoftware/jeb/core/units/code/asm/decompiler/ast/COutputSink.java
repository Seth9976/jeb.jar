package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.output.code.CppLikeDocumentPart;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ISourceCustomizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.SourceCustomizerAdapter;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class COutputSink extends CppLikeDocumentPart {
   private CDocument doc;
   private INativeDecompilerUnit decomp;
   private Boolean ovrSpaceOutCompounds;
   private Boolean ovrMergeAdjacentDefinitions;
   private Boolean ovrHideCasts;
   private Boolean ovrHideTLNS;
   private IDynamicContentManager dynamicContentManager;
   private ISourceCustomizer sourceCustomizer;
   private Deque containingClasses = new ArrayDeque();
   private Deque containingMethods = new ArrayDeque();
   private int current_method_index = -1;
   private boolean omitTypeForNextDefinitionPrinting;
   private List aststk = new ArrayList();

   public COutputSink(long var1) {
      this(var1, null, null);
   }

   public COutputSink(long var1, CDocument var3, INativeDecompilerUnit var4) {
      super(var1);
      this.doc = var3;
      this.decomp = var4;
      this.setSourceCustomizer(null);
   }

   public CDocument getDocument() {
      return this.doc;
   }

   public INativeDecompilerUnit getDecompilerUnit() {
      return this.decomp;
   }

   public void setSpaceOutCompounds(Boolean var1) {
      this.ovrSpaceOutCompounds = var1;
   }

   public boolean getSpaceOutCompounds() {
      if (this.ovrSpaceOutCompounds != null) {
         return this.ovrSpaceOutCompounds;
      } else {
         return this.doc == null ? false : this.doc.optionSpaceOutCompounds;
      }
   }

   public void setMergeAdjacentDefinitions(Boolean var1) {
      this.ovrMergeAdjacentDefinitions = var1;
   }

   public boolean getMergeAdjacentDefinitions() {
      if (this.ovrMergeAdjacentDefinitions != null) {
         return this.ovrMergeAdjacentDefinitions;
      } else {
         return this.doc == null ? false : this.doc.optionMergeAdjacentDefinitions;
      }
   }

   public void setHideCasts(Boolean var1) {
      this.ovrHideCasts = var1;
   }

   public boolean getHideCasts() {
      if (this.ovrHideCasts != null) {
         return this.ovrHideCasts;
      } else {
         return this.doc == null ? false : this.doc.optionHideCasts;
      }
   }

   public void setHideTLNS(Boolean var1) {
      this.ovrHideTLNS = var1;
   }

   public boolean getHideTLNS() {
      if (this.ovrHideTLNS != null) {
         return this.ovrHideTLNS;
      } else {
         return this.doc == null ? false : this.doc.optionHideTopLevelNamespaceElements;
      }
   }

   public boolean omitTypeForNextDefinitionPrinting() {
      return this.omitTypeForNextDefinitionPrinting;
   }

   public void setOmitTypeForNextDefinitionPrinting(boolean var1) {
      this.omitTypeForNextDefinitionPrinting = var1;
   }

   public void pushContainingClass(ICClass var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.containingClasses.push(var1);
      }
   }

   public ICClass popContainingClass() {
      return (ICClass)this.containingClasses.pop();
   }

   public ICClass getCurrentContainingClass() {
      return (ICClass)this.containingClasses.peek();
   }

   public ICClass getTopLevelClass() {
      return (ICClass)this.containingClasses.peekLast();
   }

   public void pushContainingMethod(ICMethod var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.containingMethods.push(var1);
      }
   }

   public ICMethod popContainingMethod() {
      return (ICMethod)this.containingMethods.pop();
   }

   public ICMethod getCurrentContainingMethod() {
      return (ICMethod)this.containingMethods.peek();
   }

   public void setDynamicContentManager(IDynamicContentManager var1) {
      this.dynamicContentManager = var1;
   }

   public IDynamicContentManager getDynamicContentManager() {
      return this.dynamicContentManager;
   }

   public void setSourceCustomizer(ISourceCustomizer var1) {
      if (var1 == null) {
         var1 = new SourceCustomizerAdapter();
      }

      this.sourceCustomizer = (ISourceCustomizer)var1;
   }

   public ISourceCustomizer getSourceCustomizer() {
      return this.sourceCustomizer;
   }

   public int setCurrentMethodIndex(int var1) {
      int var2 = this.current_method_index;
      this.current_method_index = var1;
      return var2;
   }

   public int getCurrentMethodIndex() {
      return this.current_method_index;
   }

   public void appendKeyword(CKeyword var1) {
      this.appendKeyword(var1.toString().toLowerCase());
   }

   public void astPush(ICElement var1) {
      Assert.a(var1 != null);
      this.aststk.add(var1);
   }

   public ICElement astPop() {
      return (ICElement)this.aststk.remove(this.aststk.size() - 1);
   }

   public ICElement astPeekSafe(int var1) {
      int var2 = this.aststk.size() - 1 - var1;
      return var2 >= 0 && var2 < this.aststk.size() ? (ICElement)this.aststk.get(var2) : null;
   }

   public int astDepth() {
      return this.aststk.size();
   }

   public void renderPreComment(ICodeCoordinates var1) {
      this.renderComment(var1, -1, true);
   }

   public void renderInlineComment(ICodeCoordinates var1, boolean var2) {
      this.renderComment(var1, 0, var2);
   }

   public void renderComment(ICodeCoordinates var1, int var2, boolean var3) {
      IDynamicContentManager var4 = this.getDynamicContentManager();
      if (var4 != null) {
         String var5 = var2 >= 0 ? var4.getComment(var1) : var4.getPreComment(var1);
         if (var5 != null && !var5.isEmpty()) {
            this.appendMultiLineComment(var5, false, var3);
         }
      }
   }

   @Override
   public void validate() {
      super.validate();
   }
}
