package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.entity.Participant;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;
import se331.rest.repository.ParticipantRepository;


@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    OrganizerRepository organizerRepository;
    @Autowired
    ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organizer org1,org2,org3;
        org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder().name("Chiang Mai").build());
        Participant par1,par2,par3,par4,par5;
        par1 = participantRepository.save(Participant.builder().name("Clark Kent").telNo("602-645-001").build());
        par2 = participantRepository.save(Participant.builder().name("Bruce Wayne").telNo("602-654-011").build());
        par3 = participantRepository.save(Participant.builder().name("Diana Prince").telNo("602-667-012").build());
        par4 = participantRepository.save(Participant.builder().name("Arthur Curry").telNo("602-623-023").build());
        par5 = participantRepository.save(Participant.builder().name("Barry Allen").telNo("602-610-044").build());
        Event tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        par1.getEventHistory().add(tempEvent);
        par2.getEventHistory().add(tempEvent);
        par3.getEventHistory().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        par2.getEventHistory().add(tempEvent);
        par3.getEventHistory().add(tempEvent);
        par4.getEventHistory().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org2);
        org2.getOwnEvents().add(tempEvent);
        par1.getEventHistory().add(tempEvent);
        par4.getEventHistory().add(tempEvent);
        par5.getEventHistory().add(tempEvent);
        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .build());
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);
        par1.getEventHistory().add(tempEvent);
        par2.getEventHistory().add(tempEvent);
        par3.getEventHistory().add(tempEvent);
    }
}
