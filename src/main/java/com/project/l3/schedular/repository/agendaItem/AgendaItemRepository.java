package com.project.l3.schedular.repository.agendaItem;

import com.project.l3.schedular.model.AgendaItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AgendaItemRepository {
    public List<AgendaItem> getAgendaItems(int meetingId);
    public AgendaItem getAgendaItem(int id);
    public AgendaItem createAgendaItem(AgendaItem agendaItem, int meetingId, int presenterId);
    public AgendaItem updateAgendaItem(AgendaItem agendaItem, int meetingId, int presenterId);
    public void deleteAgendaItem(int id);
}
