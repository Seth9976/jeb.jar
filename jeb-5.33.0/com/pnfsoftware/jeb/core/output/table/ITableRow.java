package com.pnfsoftware.jeb.core.output.table;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ITableRow {
   List getCells();
}
