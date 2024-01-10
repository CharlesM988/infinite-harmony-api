package org.kainos.ea.api;

import org.kainos.ea.cli.Job;
import org.kainos.ea.cli.JobRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.JobValidator;
import org.kainos.ea.db.JobDao;

import java.sql.SQLException;
import java.util.List;


public class JobService {
    private JobDao jobDao = new JobDao();
    private JobValidator jobValidator = new JobValidator();

    public Job getJobSpecById(int id) throws FailedToGetJobSpecException, JobSpecDoesNotExistException {
        try {
            Job job = jobDao.getJobSpecById(id);

            if (job == null) {
                throw new JobSpecDoesNotExistException();
            }

            return job;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetJobSpecException();
        }
    }

    public List<Job> getAllJobs() throws FailedToGetAllJobsException {
        List<Job> jobList;
        try {
            jobList = jobDao.getAllJobs();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetAllJobsException();
        }

        return jobList;
    }

    public void updateJob(int id, JobRequest job) throws InvalidJobException, JobDoesNotExistException, FailedToUpdateJobException {
        try {
            String validation = jobValidator.isValidJob(job);

            if (validation != null) {
                throw new InvalidJobException(validation);
            }

            Job jobToUpdate = jobDao.getJobSpecById(id);

            if (jobToUpdate == null) {
                throw new JobDoesNotExistException();
            }

            jobDao.updateJob(id, job);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateJobException();
        }
    }
}
