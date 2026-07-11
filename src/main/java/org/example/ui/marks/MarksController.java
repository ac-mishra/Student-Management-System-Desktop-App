package org.example.ui.marks;

import org.example.model.Marks;
import org.example.service.MarksService;
import org.example.service.impl.MarksServiceImpl;

import java.util.List;

public class MarksController {

    private final MarksService marksService;

    public MarksController() {

        marksService = new MarksServiceImpl();

    }

    public List<Marks> loadMarks() {

        return marksService.getAllMarks();

    }

    public boolean saveMarks(Marks marks) {

        return marksService.addMarks(marks);

    }

    public boolean updateMarks(Marks marks) {

        return marksService.updateMarks(marks);

    }

    public boolean deleteMarks(int marksId) {

        return marksService.deleteMarks(marksId);

    }

}