package com.project.l3.schedular.repository.agendaItem;

import com.project.l3.schedular.model.AgendaItem;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface AgendaItemRepository {
    public List<AgendaItem> getAgendaItems();
    public AgendaItem getAgendaItem(int id);
    public AgendaItem createAgendaItem(AgendaItem agendaItem);
    public AgendaItem updateAgendaItem(AgendaItem agendaItem);
    public void deleteAgendaItem(int id);
}
