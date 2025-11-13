package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.code.CppLikeDocumentPart;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaOutputSink extends CppLikeDocumentPart {
   private IDexDecompilerUnit dexdec;
   private JavaDocument doc;
   private IDynamicContentManager dynamicContentManager;
   private Deque containingClasses = new ArrayDeque();
   private Deque aststack = new ArrayDeque();
   private int current_method_index = -1;
   private List generatedDecompilables = new ArrayList();
   private Deque containingMethods = new ArrayDeque();
   private Map identCoordMap = new HashMap();
   private List anonymousBaseTypes = new ArrayList();
   private List anonymousCaptureInfos = new ArrayList();
   private Map generatedAnon = new HashMap();
   private List replmaps = new ArrayList();
   private boolean generatingReplacement;

   public JavaOutputSink(int var1) {
      super(var1);
   }

   public JavaOutputSink(int var1, JavaDocument var2, IDexDecompilerUnit var3) {
      super(var1);
      this.doc = var2;
      this.dexdec = var3;
   }

   public JavaDocument getDocument() {
      return this.doc;
   }

   public IDexDecompilerUnit getDecompilerUnit() {
      return this.dexdec;
   }

   public int getSortItemsForRendering() {
      return this.doc == null ? 1 : this.doc.optionSortItemsForRendering;
   }

   public boolean getUseDebugInfoNames() {
      return this.doc == null ? true : this.doc.optionUseDebugInfoNames;
   }

   public boolean getDisplayPrivateMethodsLast() {
      return this.doc == null ? false : this.doc.optionDisplayPrivateMethodsLast;
   }

   public boolean getInsertBlankLinesAfterCompounds() {
      return this.doc == null ? false : this.doc.optionInsertBlankLinesAfterCompounds;
   }

   public boolean getGenerateSyntheticFields() {
      return this.doc == null ? false : this.doc.optionGenerateSyntheticFields;
   }

   public boolean getGenerateSyntheticMethods() {
      return this.doc == null ? false : this.doc.optionGenerateSyntheticMethods;
   }

   public boolean getGenerateAnnotations() {
      return this.doc == null ? true : this.doc.optionGenerateAnnotations;
   }

   public int getDisplayMethodInternalsAsComment() {
      return this.doc == null ? 0 : this.doc.optionDisplayMethodInternalsAsComment;
   }

   public boolean getResolveMethodCallTargets() {
      return this.doc == null ? true : this.doc.optionResolveMethodCallTargets;
   }

   public boolean getResolveFieldAccessTargets() {
      return this.doc == null ? true : this.doc.optionResolveFieldAccessTargets;
   }

   public boolean getGenerateOverrideAnnotations() {
      return this.doc == null ? true : this.doc.optionGenerateOverrideAnnotations;
   }

   public boolean getGenerateLambdas() {
      return this.doc == null ? true : this.doc.optionGenerateLambdas;
   }

   public int getSplitCallArgThreshold() {
      return this.doc == null ? 0 : this.doc.optionSplitCallArgThreshold;
   }

   public boolean getDoNotGenerateThisIfPossible() {
      return this.doc == null ? false : this.doc.optionDoNotGenerateThisIfPossible;
   }

   public boolean getDisregardCollapse() {
      return this.doc == null ? false : this.doc.optionDisregardCollapse;
   }

   public void pushContainingClass(IJavaClass var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.containingClasses.push(var1);
      }
   }

   public IJavaClass popContainingClass() {
      return (IJavaClass)this.containingClasses.pop();
   }

   public IJavaClass getCurrentContainingClass() {
      return (IJavaClass)this.containingClasses.peek();
   }

   public IJavaClass getTopLevelClass() {
      return (IJavaClass)this.containingClasses.peekLast();
   }

   public boolean isBeingGenerated(IJavaElement var1) {
      for (IJavaElement var3 : this.aststack) {
         if (var3 == var1) {
            return true;
         }
      }

      return false;
   }

   public IJavaElement astPush(IJavaElement var1) {
      IJavaElement var2 = this.aststack.isEmpty() ? null : (IJavaElement)this.aststack.peek();
      this.isBeingGenerated(var1);
      this.aststack.push(var1);
      return var2;
   }

   public IJavaElement astPop() {
      return (IJavaElement)this.aststack.pop();
   }

   public IJavaElement astParent() {
      return this.aststack.isEmpty() ? null : (IJavaElement)this.aststack.peek();
   }

   public int astDepth() {
      return this.aststack.size();
   }

   public void pushContainingMethod(IJavaMethod var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.containingMethods.push(var1);
      }
   }

   public IJavaMethod popContainingMethod() {
      return (IJavaMethod)this.containingMethods.pop();
   }

   public IJavaMethod getCurrentContainingMethod() {
      return (IJavaMethod)this.containingMethods.peek();
   }

   public Collection getContainingMethods() {
      return Collections.unmodifiableCollection(this.containingMethods);
   }

   public void setDynamicContentManager(IDynamicContentManager var1) {
      this.dynamicContentManager = var1;
   }

   public IDynamicContentManager getDynamicContentManager() {
      return this.dynamicContentManager;
   }

   public int setCurrentMethodIndex(int var1) {
      int var2 = this.current_method_index;
      this.current_method_index = var1;
      return var2;
   }

   public int getCurrentMethodIndex() {
      return this.current_method_index;
   }

   public void appendKeyword(JavaKeyword var1) {
      this.appendKeyword(var1.toString().toLowerCase());
   }

   public void appendKeyword(JavaKeyword var1, long var2) {
      this.appendKeyword(var1.toString().toLowerCase(), var2);
   }

   public void renderPreComment(ICodeCoordinates var1) {
      this.renderComment(var1, -1, true);
   }

   public void renderInlineComment(ICodeCoordinates var1, boolean var2) {
      this.renderComment(var1, 0, var2);
   }

   private void renderComment(ICodeCoordinates var1, int var2, boolean var3) {
      this.setEolComment(null);
      IDynamicContentManager var4 = this.getDynamicContentManager();
      if (var4 != null) {
         String var5 = var2 >= 0 ? var4.getComment(var1) : var4.getPreComment(var1);
         if (var5 != null) {
            this.appendMultiLineComment(var5, false, var3);
         }
      }
   }

   public void loadCommentInline(ICodeCoordinates var1) {
      this.setEolComment(null);
      IDynamicContentManager var2 = this.getDynamicContentManager();
      if (var2 != null) {
         String var3 = var2.getComment(var1);
         if (var3 != null) {
            this.onEolAddComment(var3);
         }
      }
   }

   public void appendAnnotationsList(List var1, char var2) {
      this.appendAnnotationsList(var1, var2, null);
   }

   public void appendAnnotationsList(List var1, char var2, Set var3) {
      for (IJavaAnnotation var5 : var1) {
         if (var3 == null || !var3.contains(var5.getType().toString())) {
            var5.generate(this, var2);
         }
      }
   }

   public void recordIdentifierCoordinates(long var1, ICodeCoordinates var3) {
      Object var4 = (List)this.identCoordMap.get(var1);
      if (var4 == null) {
         var4 = new ArrayList();
         this.identCoordMap.put(var1, var4);
      }

      var4.add(var3);
   }

   public Map getIdentifierCoordinates() {
      return this.identCoordMap;
   }

   public void pushAnonymousBaseType(IJavaType var1) {
      this.anonymousBaseTypes.add(var1);
   }

   public IJavaType popAnonymousBaseType() {
      return (IJavaType)this.anonymousBaseTypes.remove(this.anonymousBaseTypes.size() - 1);
   }

   public IJavaType getCurrentAnonymousBaseType() {
      return this.anonymousBaseTypes.isEmpty() ? null : (IJavaType)this.anonymousBaseTypes.get(this.anonymousBaseTypes.size() - 1);
   }

   public void pushAnonymousCaptureInfo(JavaOutputSink.CaptureInfo var1) {
      this.anonymousCaptureInfos.add(var1);
   }

   public JavaOutputSink.CaptureInfo popAnonymousCaptureInfo() {
      return (JavaOutputSink.CaptureInfo)this.anonymousCaptureInfos.remove(this.anonymousCaptureInfos.size() - 1);
   }

   public JavaOutputSink.CaptureInfo getCurrentAnonymousCaptureInfo() {
      return this.anonymousCaptureInfos.isEmpty() ? null : (JavaOutputSink.CaptureInfo)this.anonymousCaptureInfos.get(this.anonymousCaptureInfos.size() - 1);
   }

   public void addGeneratedAnon(IJavaElement var1, IJavaClass var2) {
      Maps.putMulti(this.generatedAnon, var1, var2);
   }

   public List getGeneratedAnon(IJavaElement var1) {
      List var2 = (List)this.generatedAnon.get(var1);
      if (var2 == null) {
         var2 = Collections.emptyList();
      }

      return var2;
   }

   public void pushReplacements(Map var1) {
      this.replmaps.add(var1);
   }

   public Map popReplacements() {
      return (Map)this.replmaps.remove(this.replmaps.size() - 1);
   }

   public IJavaExpression getReplacementFor(IJavaIdentifier var1) {
      for (int var2 = this.replmaps.size() - 1; var2 >= 0; var2--) {
         IJavaExpression var3 = (IJavaExpression)((Map)this.replmaps.get(var2)).get(var1);
         if (var3 != null) {
            return var3;
         }
      }

      return null;
   }

   public void setGeneratingReplacement(boolean var1) {
      this.generatingReplacement = var1;
   }

   public boolean requestGeneratingReplacement() {
      if (this.generatingReplacement) {
         return false;
      } else {
         this.generatingReplacement = true;
         return true;
      }
   }

   public void doneGeneratingReplacement() {
      this.generatingReplacement = false;
   }

   public void recordGeneratedDecompilable(String var1) {
      if (var1 != null) {
         this.generatedDecompilables.add(var1);
      }
   }

   public List getDecompilables() {
      return Collections.unmodifiableList(this.generatedDecompilables);
   }

   @Override
   public void validate() {
      super.validate();
      if (!this.containingClasses.isEmpty()) {
         throw new JebRuntimeException("Class container stack should be empty!");
      } else if (!this.containingMethods.isEmpty()) {
         throw new JebRuntimeException("Method container stack should be empty!");
      } else if (!this.aststack.isEmpty()) {
         throw new JebRuntimeException("AST stack should be empty!");
      }
   }

   public static class CaptureInfo {
      public List initializerArgs;
      public Map synth = new HashMap();
   }
}
